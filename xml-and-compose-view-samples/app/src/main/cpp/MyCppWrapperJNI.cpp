// MyCppWrapperJNI.cpp

#include <jni.h>
#include "MyCppWrapper.hpp"

extern "C" JNIEXPORT void JNICALL
Java_com_example_javaviewtest_cpp_MyCppWrapper_printMessage(JNIEnv *env, jobject obj) {
    printMessage();
}