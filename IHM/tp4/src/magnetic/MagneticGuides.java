package magnetic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.canvas.transitions.PressOnTag;
import fr.lri.swingstates.debug.StateMachineVisualization;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Click;
import fr.lri.swingstates.sm.transitions.Drag;
import fr.lri.swingstates.sm.transitions.MouseOnPosition;
import fr.lri.swingstates.sm.transitions.Press;
import fr.lri.swingstates.sm.transitions.Release;

public class MagneticGuides extends JFrame {

	private static final long serialVersionUID = -1258141027456956587L;
	private Canvas canvas;
	private CExtensionalTag oTag;
	private int width;
	private int height;
	private CStateMachine stateMachine;

	public MagneticGuides(String title, int width, int height) {
		super(title);
		this.width = width;
		this.height = height;
		canvas = new Canvas(width, height);
		canvas.setAntialiased(true);
		getContentPane().add(canvas);
		//oTag = new CExtensionalTag(canvas) {
		//};
		oTag = new MagneticGuide(canvas);

		stateMachine = new CStateMachine() {

			private Point2D p;
			private CShape draggedShape;

			public State start = new State() {
				
				Transition pressOnObject = new PressOnTag(oTag, BUTTON1,
						">> oDrag") {
					public void action() {
						p = getPoint();
						draggedShape = getShape();
					}
				};
				
				MouseOnPosition controlClickOnCanvas = new Click(BUTTON1, CONTROL) {
					public void action() {
						final CSegment segment = canvas.newSegment(0,  getPoint().getY(), MagneticGuides.this.width, getPoint().getY());
						segment.addTag(oTag);
						segment.setAntialiased(true);
						segment.setPickable(true);
						segment.setTransparencyOutline(0.5f);
						segment.setTransparencyFill(0.5f);
						segment.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
						segment.belowAll();
					}
				};
				
				Transition controlShiftClickOnCanvas = new Press(BUTTON1, CONTROL_SHIFT) {
					public void action() {
						final CSegment segment = canvas.newSegment(getPoint().getX(), 0, getPoint().getX(), MagneticGuides.this.height);
						segment.addTag(oTag);
						segment.setAntialiased(true);
						segment.setPickable(true);
						segment.setTransparencyOutline(0.5f);
						segment.setTransparencyFill(0.5f);
						segment.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
						segment.belowAll();
					}
				};
				
			};

			public State oDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint();
						draggedShape.translateBy(q.getX() - p.getX(), q.getY()
								- p.getY());
						p = q;
					}
				};
				Transition release = new Release(BUTTON1, ">> start") {
				};
			};

		};
		stateMachine.attachTo(canvas);

		pack();
		setVisible(true);
		canvas.requestFocusInWindow();
	}
	
	public CStateMachine getStateMachine() {
		return stateMachine;
	}

	public void populate() {
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		double s = (Math.random() / 2.0 + 0.5) * 30.0;
		double x = s + Math.random() * (width - 2 * s);
		double y = s + Math.random() * (height - 2 * s);

		int red = (int) ((0.8 + Math.random() * 0.2) * 255);
		int green = (int) ((0.8 + Math.random() * 0.2) * 255);
		int blue = (int) ((0.8 + Math.random() * 0.2) * 255);

		CRectangle r = canvas.newRectangle(x, y, s, s);
		r.setFillPaint(new Color(red, green, blue));
		r.addTag(oTag);
	}

	public static void main(String[] args) {
		MagneticGuides guides = new MagneticGuides("Magnetic guides", 600, 600);
		for (int i = 0; i < 20; ++i)
			guides.populate();
		guides.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JFrame frame = new JFrame();
		final StateMachineVisualization visu = new StateMachineVisualization(guides.getStateMachine());
		frame.getContentPane().add(visu);
		frame.pack();
		frame.setVisible(true);
	}

}
