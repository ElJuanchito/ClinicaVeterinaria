package co.edu.uniquindio.clinicaVeterinaria.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Amador
 */
public class FxUtility {
	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
					abrirContextMenu(tf, "Este campo solo puede tener numeros");
				}
			}

		});
	}

	public static void setAsAlphanumericTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[a-zA-z\\d\\s]*")) {
					tf.setText(newValue.replaceAll("[^\\[a-zA-z\\d\\s]", ""));
					abrirContextMenu(tf, "Este campo solo puede tener caracteres");
				}
			}

		});
	}

	public static void setAsIntegerWithSymbolTextField(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[\\d:]*")) {
					tf.setText(newValue.replaceAll("[^\\d:]", ""));
					abrirContextMenu(tf, "Este campo solo permite enteros y el símbolo ':'");
				}
			}
		});
	}

	public static void setAsHourTextField(TextField tf) {
		tf.setText("00:00");

		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches(":|[0-9]{1,2}:[0-9]{0,2}")) {
					tf.setText("00:00");
					abrirContextMenu(tf, "Ingresa la hora en formato 'HH:mm'");
				}
			}
		});
	}

	private static void abrirContextMenu(Node nodo, String msg) {
		final ContextMenu menu = new ContextMenu();
		menu.setStyle("-fx-text-fill: black;");
		menu.getItems().add(new MenuItem(msg));
		menu.show(nodo, Side.BOTTOM, 0, 0);

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
			menu.hide();
		}));
		timeline.play();
	}

	public static void setAsIntegerTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					Integer.parseInt(tf.getText());
				} catch (Exception e) {
					observable.removeListener(this);
					tf.setText(oldValue);
					observable.addListener(this);
					abrirContextMenu(tf, "Este campo solo puede tener numeros enteros");
				}
			}
		});
	}

	public static void setAsIntegerTextfield(TextField tf, int minValue, int maxValue) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					int num = Integer.parseInt(tf.getText());
					if (num >= minValue && num <= maxValue)
						return;
				} catch (Exception e) {
				}
				observable.removeListener(this);
				tf.setText(oldValue);
				observable.addListener(this);
				abrirContextMenu(tf, "Este campo solo puede tener numeros entre " + minValue + " y " + maxValue);
			}
		});
	}

	public static void cambiarTituloStage(Parent parent, String title) {
		parent.sceneProperty().addListener(((observableScene, oldScene, newScene) -> {
			parent.getScene().windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
				Stage stage = (Stage) parent.getScene().getWindow();
				stage.setTitle(title);
			});
		}));

	}

	public static ButtonType crearDecisionAlerta(String titulo, String header, String contenido, AlertType alertType,
			double width, ButtonType... buttonTypes) {
		Alert alerta = new Alert(alertType, contenido, buttonTypes);
		alerta.setTitle(titulo);
		alerta.setHeaderText(header);
		alerta.setWidth(width);
		return alerta.showAndWait().orElse(null);
	}

	public static void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.show();
	}

	public static void generarAdvertenciaSobreescritura(Runnable metodo) {
		ButtonType boton = crearDecisionAlerta("Decision", "Sobreescritura de campos",
				"Advertencia de sobreescritura de campos", AlertType.INFORMATION, 700, ButtonType.OK, ButtonType.CLOSE);
		if (boton == ButtonType.OK)
			metodo.run();
	}

	public static void setMaximumTextSize(TextField tf, int tamanio) {
		tf.setTextFormatter(new TextFormatter<String>(cambio -> {
			if (cambio.isContentChange()) {
				if (cambio.getControlNewText().length() > tamanio) {
					abrirContextMenu(tf, "Este campo puede tener maximo " + tamanio + " caracteres");
					return null;
				}
			}
			return cambio;
		}));
	}
}