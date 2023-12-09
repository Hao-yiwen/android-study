package com.example.chapter07_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chapter07_client.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_contact_name;
    private EditText et_contact_phone;
    private EditText et_contact_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        et_contact_name = findViewById(R.id.et_contact_name);
        et_contact_phone = findViewById(R.id.et_contact_phone);
        et_contact_email = findViewById(R.id.et_contact_email);
        findViewById(R.id.btn_add_contact).setOnClickListener(this);
        findViewById(R.id.btn_read_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_contact) {
            Contact contact = new Contact();
            contact.name = et_contact_name.getText().toString().trim();
            contact.phone = et_contact_phone.getText().toString().trim();
            contact.email = et_contact_email.getText().toString().trim();
            // 方式一 使用ContentResolver多次写入 每次一个字段
//            addContact(getContentResolver(), contact);
            // 方式二 批处理方式
            // 每次操作都是一个ContentProviderOperation 构建一个操作集合 然后一次执行
            // 好处是 要么全部成功 要么全部失败 保证事务一致性
            addFullContact(getContentResolver(), contact);
        } else if (v.getId() == R.id.btn_read_contact) {
            readContact(getContentResolver());
        }
    }

    // 查询通讯信息
    @SuppressLint("Range")
    private void readContact(ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{ContactsContract.RawContacts._ID}, null, null, null, null);
        while (cursor.moveToNext()) {
            int rawContactId = cursor.getInt(0);
            Uri uri = Uri.parse("content://com.android.contacts/contacts/" + rawContactId + "/data");
            Cursor dataCursor = contentResolver.query(uri, new String[]{Contacts.Data.MIMETYPE, Contacts.Data.DATA1, Contacts.Data.DATA2}, null, null, null, null);
            Contact contact = new Contact();
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(dataCursor.getColumnIndex(Contacts.Data.DATA1));
                String mimeType = dataCursor.getString(dataCursor.getColumnIndex(Contacts.Data.MIMETYPE));
                String data2 = dataCursor.getString(dataCursor.getColumnIndex(Contacts.Data.DATA2));
                switch (mimeType) {
                    case ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE:
                        contact.name = data2;
                        break;
                    case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                        contact.phone = data1;
                        break;
                    case ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE:
                        contact.email = data1;
                        break;
                }
            }
            dataCursor.close();
            if (contact.name != null) {
                Log.d("ning", "readContact: " + contact.toString());
            }
        }
        cursor.close();
    }

    private void addFullContact(ContentResolver contentResolver, Contact contact) {
        ContentProviderOperation op_main = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build();

        ContentProviderOperation op_name = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(Contacts.Data.DATA2, contact.name)
                .build();

        ContentProviderOperation op_phone = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(Contacts.Data.DATA1, contact.phone)
                .withValue(Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build();

        ContentProviderOperation op_email = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(Contacts.Data.DATA1, contact.email)
                .withValue(Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .build();

        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        operations.add(op_main);
        operations.add(op_name);
        operations.add(op_phone);
        operations.add(op_email);

        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // 往手机通讯录中添加一个联系人信息 （包含姓名 手机号码 电子邮箱）
    private void addContact(ContentResolver contentResolver, Contact contact) {
        ContentValues values = new ContentValues();
        Uri uri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(uri);

        ContentValues name = new ContentValues();
        name.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        name.put(Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        name.put(Contacts.Data.DATA2, contact.name);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, name);

        ContentValues phone = new ContentValues();
        phone.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        phone.put(Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        phone.put(Contacts.Data.DATA1, contact.phone);
        // 联系类型 1表示家庭 2表示工作
        phone.put(Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, phone);

        ContentValues email = new ContentValues();
        email.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        email.put(Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        email.put(Contacts.Data.DATA1, contact.email);
        // 联系类型 1表示家庭 2表示工作
        email.put(Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, email);
    }
}