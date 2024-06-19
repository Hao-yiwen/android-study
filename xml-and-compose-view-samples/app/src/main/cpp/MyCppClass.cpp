#include "MyCppClass.hpp"
#include <android/log.h>

#define LOG_TAG "MyCppProject"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

void MyCppClass::printMessage() {
    LOGI("Hello, From C++!");
}