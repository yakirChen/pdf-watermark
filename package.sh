version=$1

export VERSION="1.1.0"
export JAVA_HOME=`/usr/libexec/java_home -v $version`

echo "JDK Version $version"
echo "JAVA HOME $JAVA_HOME"

mvn clean install -Dmaven.test.skip=true -P${version} && \
  cd pdf-watermark-swing && \
  rm -rf libs && \
  mkdir libs && \
  mvn clean package -Dmaven.test.skip=true -P${version} && \
  cp target/pdf-watermark-swing-${VERSION}.jar libs

jpackage --input libs/ \
  --name PDFWatermark \
  --main-jar pdf-watermark-swing-${VERSION}.jar \
  --main-class io.github.yakirchen.watermark.swing.WatermarkApp \
  --type dmg \
  --app-version ${VERSION} \
  --icon src/main/resources/icon.icns \
  --java-options '--enable-preview -Xdock:name=PDF水印 -XX:+UseG1GC -Xms200M -Xmx200M' \
  --jlink-options '--strip-native-commands --strip-debug --no-man-pages --no-header-files --vm=server' \
  --module-path ${JAVA_HOME}/jmods \
  --add-modules java.desktop,jdk.unsupported,java.xml,java.logging,jdk.unsupported.desktop

# jlink --compress=2 选项会增加 runtime lib中 shaders.metallib 文件的体积