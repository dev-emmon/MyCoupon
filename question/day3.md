## 问题 1
项目打包遇com.android.builder.internal.aapt.v2.Aapt2Exception: AAPT2 error: check logs for details

Android Studio升级到3.1之后会遇到这样的相关错误：

> java.util.concurrent.ExecutionException: com.android.builder.internal.aapt.v2.Aapt2Exception: AAPT2 error: check logs for details

查了网上很多处理都是在项目的gradle.properties中添加下面的配置，再编译就通过了：

> android.enableAapt2=false

但是但是在打包的时候又出现这样的问题了，Google、百度了好多遍都没能解决这个问题，最后换种方式查了一下，了解到的解决方法是，在app的build.gradle中添加以下配置：

> aaptOptions.cruncherEnabled = false
> aaptOptions.useNewCruncher = false

添加到如下的位置，编译之后，打包时是成功的。 

![](../images/build_apk_error.png)