This project starting port of aparapi for Android on Nexus 4 devices(though it can be made to work on other phone devices).

## Clone code ##
Here are steps to create android app (ensure java-1.6, ant, gcc > 4.6, android-ndk >= [r8](https://code.google.com/p/aopencl/source/detail?r=8) , android-sdk )are present):
  * hg clone https://sgorti@code.google.com/p/aopencl/
  * cd aopencl/aparapi\_68

## Environement setup ##
  * create a shell script env1.sh that look like:
```
cat env1.sh 
P1=/opt/android/android-sdk-linux/platform-tools/:/opt/android/android-sdk-linux/tools/:/opt/android-ndk-r8d
export NDK=/opt/android-ndk-r8d/
export PATH=$P1:$PATH
```
  * Source . ./env1.sh

## Build JNI library ##
  * ndk-build   #OR ndk-build NDK\_DEBUG=1 V=1
    * This will build libaparapi.so

## Build Android app ##
  * Now build java application

  * android update project -p . -n aparapi -t 11
    * Above command will create build.xml for ant command
## Create the classpath as resource for reflection ##
  * ant debug
    * This will build application. But we need to package all generated class files (so that aparapi can generate the OpenCL program in runtime)
    * So as a hack, run following command in order to package the all class files in Android application (in future need to write build-target)
      * cd bin/classes/;jar cvf ../../res/raw/myapp.jar com org;cd ../..
      * ant debug
        * This command will pack the res/raw/myapp.jar in Android applcation
        * We need to repeast this step only with OpenCL-Kernel related java files changed

## Install the App ##
  * Connect phone via USB-cable to computer (Enable 'USB Debugging' in settings)
    * Ensure 'adb devices' shows your phone device
  * adb install bin/aparapi-debug.apk
  * Execute the 'Aparapi on Android' in your phone


## Limitation of current port ##

  * No float, double, boolean and their corresponding arrays
  * If OpenCL kernel is written in inner class , QualComm OpenCL SDK might crash Dalivik-JVM
  * It is still in alpha stages :)