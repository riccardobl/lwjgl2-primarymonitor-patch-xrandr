# LWJGL2 Primary monitor Patch

A java agent that makes lwjgl2 use the correct primary monitor.

## Usage

1. Clone the repo

2. Build
```bash
gradle build 
```

3. Get the generated jar from build/libs/lwjgl2-primarymonitor-patch-xrandr.jar

4. Add javassist to your project's dependencies

```gradle
	compile "org.javassist:javassist:3.19.0-GA"
```

5. Run your project with 
```java
-javaagent:/path/of/lwjgl2-primarymonitor-patch-xrandr.jar
```


eg. 

```bash
java -javaagent:$PWD/lwjgl2-primarymonitor-patch-xrandr.jar -jar XYZ.jar
 ```
