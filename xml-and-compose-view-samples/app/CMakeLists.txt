cmake_minimum_required(VERSION 3.4.1)

# 设置源文件路径
add_library(native-lib SHARED src/main/cpp/native-lib.cpp)

# 查找 log 库
find_library(log-lib log)

# 链接 log 库
target_link_libraries(native-lib ${log-lib})
