package io.github.yakirchen.watermark;

import io.github.yakirchen.watermark.api.PDFEntity;
import io.github.yakirchen.watermark.core.PDFManager;
import io.github.yakirchen.watermark.core.PDFWatermark;
import io.github.yakirchen.watermark.core.Watermark;
import io.github.yakirchen.watermark.log.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static io.github.yakirchen.watermark.core.Constants.HOME;
import static javafx.stage.FileChooser.ExtensionFilter;

/**
 * WatermarkController
 *
 * @author yakir on 2021/09/10 19:44.
 */
public class WatermarkController implements Initializable {

    @FXML
    private TextField                   tfMarkText;
    @FXML
    private Spinner<Integer>            spinnerFontSize;
    @FXML
    private ColorPicker                 colorPicker;
    @FXML
    private TableView<PDFEntity>        pdfTable;
    @FXML
    private TableColumn<String, String> colName;
    @FXML
    private TableColumn<String, String> colPath;
    @FXML
    private ImageView                   preview;

    private final ObservableList<PDFEntity> TABLE_DATA = FXCollections.observableArrayList();

    private FileChooser pdfChooser;
    private Stage       stage;
    private Integer     fontSize = 130;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.pdfChooser = new FileChooser();
        pdfChooser.setTitle("选择PDF文件");
        pdfChooser.getExtensionFilters()
                .addAll(new ExtensionFilter("PDF", "*.pdf"));
        pdfChooser.initialDirectoryProperty()
                .set(new File(HOME));

        var spinnerValueFactory = new IntegerSpinnerValueFactory(10, 1000, fontSize);
        spinnerFontSize.setValueFactory(spinnerValueFactory);

        pdfTable.setItems(TABLE_DATA);
        pdfTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPath.setCellValueFactory(new PropertyValueFactory<>("path"));

        var color = new Color(1, 0, 0, 0.2);
        colorPicker.setValue(color);
    }

    @FXML
    public void menuPdfAddAction() {
        selectPdfFiles();

    }

    @FXML
    public void menuPdfRemoveAction() {

        removeSelected();

    }

    @FXML
    public void btnPdfAddAction() {

        selectPdfFiles();

    }

    @FXML
    public void btnPdfRemoveAction() {

        removeSelected();

    }

    @FXML
    public void btnPreviewAction() {

        var bytes = PDFManager.createEmptyPdf();
        if (null == bytes || bytes.length < 1) {
            return;
        }

        var color = colorPicker.getValue();
        var watermark = new Watermark()
                .setOriginBytes(bytes)
                .setAlpha((float) color.getOpacity())
                .setColorRGB((int) color.getRed() * 255, (int) color.getGreen() * 255, (int) color.getBlue() * 255)
                .setFontSize(spinnerFontSize.getValue())
                .setText(Optional.ofNullable(tfMarkText.getText()).filter(_text -> !_text.isBlank()).orElse("水印"));

        var retBytes = PDFWatermark.builder(watermark).preview();
        if (null == retBytes) {
            return;
        }

        var image = new Image(new ByteArrayInputStream(retBytes));
        preview.setImage(image);
        preview.autosize();
    }

    @FXML
    public void btnGenerateAction() {

        List<PDFEntity> pdfEntityList = this.pdfTable.getItems();

        var color = colorPicker.getValue();
        var watermark = new Watermark()
                .setAlpha((float) color.getOpacity())
                .setColorRGB((int) color.getRed() * 255, (int) color.getGreen() * 255, (int) color.getBlue() * 255)
                .setFontSize(spinnerFontSize.getValue())
                .setText(Optional.ofNullable(tfMarkText.getText()).filter(_text -> !_text.isBlank()).orElse("水印"));

        for (PDFEntity pdfEntity : pdfEntityList) {
            watermark.setOrigin(pdfEntity.getPath());
            PDFWatermark.builder(watermark).mark();
        }
    }

    private void removeSelected() {

        var selected = new ArrayList<>(pdfTable.getSelectionModel().getSelectedItems());

        if (!selected.isEmpty()) {
            TABLE_DATA.removeAll(selected);
            pdfTable.getSelectionModel().clearSelection();
        }
    }

    private void selectPdfFiles() {

        List<File> files = pdfChooser.showOpenMultipleDialog(stage);

        List<PDFEntity> entities = Optional.ofNullable(files)
                .stream()
                .flatMap(Collection::stream)
                .map(_file -> {
                    var pdfEntity = new PDFEntity();
                    pdfEntity.setName(_file.getName());
                    pdfEntity.setPath(_file.getAbsolutePath());
                    return pdfEntity;
                })
                .collect(Collectors.toList());

        Log.info("选择文件数量: {}", entities.size());

        TABLE_DATA.addAll(entities);
    }

}
