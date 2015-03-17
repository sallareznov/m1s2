package transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class CustomTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 5297375336987496610L;

	@Override
	protected Transferable createTransferable(JComponent c) {
		return new StringSelection(((JLabel) c).getText());
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
	}

	@Override
	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}
	
	@Override
	public boolean canImport(TransferSupport support) {
		return support.isDataFlavorSupported(DataFlavor.stringFlavor);
	}
	
	@Override
	public boolean importData(TransferSupport support) {
		System.out.println(((JFormattedTextField) support.getComponent()).getText());
		return super.importData(support);
	}
	
}
