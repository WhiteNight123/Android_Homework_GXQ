# MVP Demo

## 简介

M层Model负责数据处理
P层Presenter负责调控 View与Model之间的交互
V层View负责UI和用户交互

![MVP](https://github.com/WhiteNight123/Redrock_Homework_Android4.mvp.png)

## 流程

1. Activity初始化UI,实例化Presenter,实现View接口,监听界面

2. 接收到加载事件后,通过Presenter处理.Presenter执行加载

3. Presenter显示进度条,并让Model去处理数据,同时把LoadListener(自身)传给Model

4. Model处理完后通过LoadListener回调给Presenter

5. Presenter把结果返回给Activity,最后Activity显示结果

   ![MVP](https://github.com/WhiteNight123/Redrock_Homework_Android4.mvp.jpg)

## 心得

第一次使用MVP架构,感觉MVP最大的优点是降低耦合,但需要写各种类,各种回调,会让原本的代码变复杂,目前小项目感觉没必要用MVP