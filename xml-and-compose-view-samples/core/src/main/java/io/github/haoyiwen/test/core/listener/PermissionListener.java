package io.github.haoyiwen.test.core.listener;

import java.util.List;

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
