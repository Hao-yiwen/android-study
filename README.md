# 介绍

本仓库为 Anroid 学习代码仓库

## 文档

[java android 学习链接](https://www.bilibili.com/video/BV19U4y1R7zV/?p=64&spm_id_from=pageDriver&vd_source=e2ad92335ca8373f02f0c6b05e039a53)

[kotlin android 学习链接](https://developer.android.com/courses/android-basics-compose/course)

## 仓库介绍

| 仓库名称                                               | 描述                                                                              | 难度 |
| ------------------------------------------------------ | --------------------------------------------------------------------------------- | ---- |
| xml-and-compose-view-samples                           | 后续 compose 和 xml_views 以及 Android 集成 RN 容器的示例代码都将集中在这个仓库中 | 初级 |
| java-view-quick-start                                  | android 开发起步项目，目前已重新整理，可浏览其中组件和 api 示例                   | 初级 |
| 30DaysOfWellness                                       | 使用 lazyColumn 实现 30 天健康目标 app                                            | 初级 |
| basic-android-kotlin-compose-training-affirmations     | compose 列表简单示例                                                              | 初级 |
| basic-android-kotlin-compose-training-cupcake          | compose 导航练习                                                                  | 初级 |
| basic-android-kotlin-compose-training-dessert-clicker  | 生命周期学习及 bug 修复，后期引入 viewModel                                       | 初级 |
| basic-android-kotlin-compose-training-tip-calculator   | compose 状态及简单 ui 练习                                                        | 初级 |
| basic-android-kotlin-compose-training-unscramble       | compose 中引入 viewModel 学习及其练习                                             | 初级 |
| basic-android-kotlin-compose-training-woof             | Material 组件使用练习                                                             | 初级 |
| DiceRoller                                             | compose 入门 app 练习,幸运筛子                                                    | 初级 |
| GreetingCard                                           | compose 入门 app 练习，kotlin 基础练习                                            | 初级 |
| gridExample                                            | compose 入门 app 练习，网格布局练习                                               | 初级 |
| kotlinAndroid                                          | compose 入门 app 练习，kotlin 基础练习                                            | 初级 |
| LifeCycle                                              | 传统 java 生命周期练习                                                            | 初级 |
| SuperHeroes                                            | 超级英雄 app 自己实现练习                                                         | 初级 |
| basic-android-kotlin-compose-training-lunch-tray       | compsoe 页面之间导航                                                              | 初级 |
| basic-android-kotlin-compose-training-reply-app        | 构建适配多尺寸页面的应用                                                          | 初级 |
| basic-android-kotlin-compose-training-sports           | 构建适配多尺寸页面联系                                                            | 初级 |
| basic-android-kotlin-compose-training-race-tracker     | 协程初体验                                                                        | 初级 |
| basic-android-kotlin-compose-training-mars-photos      | 使用 retrofit 获取数据                                                            | 初级 |
| Amphibians                                             | 使用 retrofit/coil 获取数据练习                                                   | 初级 |
| BookShelf                                              | 使用 retrofit/coil 获取数据,异步同时请求多条数据                                  | 初级 |
| nestedScrollview                                       | 使用 nested 复现 rn 中 scrollview 嵌套滚动问题                                    | 初级 |
| basic-android-kotlin-compose-training-bus-schedule-app | Room 学习                                                                         | 初级 |
| FlightSearch                                           | Room 和 datastore 练习                                                            | 初级 |
| basic-android-kotlin-compose-training-workmanager      | workmanager 学习                                                                  | 初级 |
| basic-android-kotlin-compose-training-waterme          | workmanager 练习                                                                  | 初级 |
| rnIntegation                                           | Android 集成 rn 容器练习                                                          | 初级 |
| codelab-android-navigation                             | Android navigation grph 学习，使用与 view 的导航组件                              | 初级 |
| rnAndroidDynamicImport                                 | Android 加载远程 rnbundle,rn 动态化最小 demo                                      | 高级 |

## 问题

1. 在 android 和 rn 代码分离后，如果 rn 要引入第三方依赖，需要手动链接引入，但是引入出现报错怎么办?

- 直接引入 aar 包，不要尝试引用 node_modules 中的 android 项目，否则管理依赖会累死
