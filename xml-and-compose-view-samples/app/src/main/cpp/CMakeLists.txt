cmake_minimum_required(VERSION 3.4.1)

# 设置项目名称
project(MyCppProject)

# 添加库文件，包含所有源文件
add_library(
        MyCppProject
        SHARED
        MyCppClass.cpp
        MyCppWrapper.cpp
        MyCppWrapperJNI.cpp
        native-lib.cpp
)

# 查找 log 库
find_library(log-lib log)

# 指定其他.so库的位置并链接
add_library(MyCppProject1 SHARED IMPORTED)
set_target_properties(MyCppProject1 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/arm64-v8a/libMyCppProject1.so)

# 链接 log 库和自定义库，加入c++_shared或c++_static库
target_link_libraries(MyCppProject ${log-lib} MyCppProject1 c++_shared)