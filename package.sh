export JAVA_HOME=$(/usr/libexec/java_home version)

mvn clean install -Dmaven.test.skip=true

cd pdf-watermark-swing

rm -rf libs

mvn clean package -Dmaven.test.skip=true

cp target/pdf-watermark-swing-1.0.0-SNAPSHOT.jar libs

jpackage --input libs/ \
  --name PDFWatermark \
  --main-jar pdf-watermark-swing-1.0.0-SNAPSHOT.jar \
  --main-class io.github.yakirchen.watermark.swing.WatermarkApp \
  --type dmg \
  --icon src/main/resources/icon.icns \
  --java-options '--enable-preview -Xdock:name=PDF水印' \
  --module-path ${JAVA_HOME}/jmods \
  --add-modules java.desktop,jdk.unsupported,java.xml,java.logging,jdk.unsupported.desktop
