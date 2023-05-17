version=$1

export VERSION="1.1.0"
export JAVA_HOME=`/usr/libexec/java_home -v $version`

echo "JDK Version $version"
echo "JAVA HOME $JAVA_HOME"

mvn clean install -Dmaven.test.skip=true && \
  cd pdf-watermark-fx && \
  rm -rf libs && \
  mkdir libs && \
  mvn clean package -Dmaven.test.skip=true && \
  cp target/pdf-watermark-fx-${VERSION}.jar libs

#cd pdf-watermark-fx

jpackage --input libs/ \
  --name PDFWatermarkFx \
  --main-jar pdf-watermark-fx-${VERSION}.jar \
  --main-class io.github.yakirchen.watermark.WatermarkAppFx \
  --type dmg \
  --icon src/main/resources/icon.icns \
  --java-options '--enable-preview -Xdock:name=PDF水印 -XX:+UseZGC -Xms100M -Xmx100M' \
  --jlink-options '--strip-native-commands --strip-debug --no-man-pages --no-header-files --vm=server' \
  --module-path ${JAVA_HOME}/jmods:/Volumes/sm/servers/javafx-jmods-17.0.0.1 \
  --add-modules java.desktop,jdk.unsupported,java.xml,java.logging,jdk.unsupported.desktop,javafx.base,javafx.controls,javafx.fxml,javafx.swing,javafx.graphics,javafx.web

# jlink --compress=2 选项会增加 runtime lib中 shaders.metallib 文件的体积