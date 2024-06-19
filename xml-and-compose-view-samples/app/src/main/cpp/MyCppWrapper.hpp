#ifndef MYCPPWRAPPER_H
#define MYCPPWRAPPER_H

#include <jni.h>
#include "MyCppClass.hpp"  // 确保包含 MyCppClass.hpp 头文件

#ifdef __cplusplus
extern "C" {
#endif

void printMessage();

JNIEXPORT void JNICALL Java_com_example_javaviewtest_cpp_MyCppWrapper_printMessage(JNIEnv*, jobject);

#ifdef __cplusplus
}
#endif

#endif // MYCPPWRAPPER_H