package button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.canvas.transitions.EnterOnShape;
import fr.lri.swingstates.canvas.transitions.LeaveOnShape;
import fr.lri.swingstates.canvas.transitions.PressOnShape;
import fr.lri.swingstates.canvas.transitions.ReleaseOnShape;
import fr.lri.swingstates.debug.StateMachineVisualization;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Release;
import fr.lri.swingstates.sm.transitions.TimeOut;

public class SimpleButton {

    private CText label;
    private CExtensionalTag selected;
    private CStateMachine sm;

    public SimpleButton(final Canvas canvas, String text) {
	   label = canvas.newText(0, 0, text, new Font("verdana", Font.PLAIN, 12));
	   selected = new CExtensionalTag(canvas) { };
	   final CRectangle rect = canvas.newRectangle(label.getCenterX() - 25, label.getCenterY() - 15, 50, 30);
	   rect.setFillPaint(Color.WHITE);
	   rect.setStroke(new BasicStroke(2));
	   rect.addTag(selected);
	   label.addTag(selected);
	   label.above(rect);
	   selected.setPickable(true);
	   rect.setPickable(true);
	   sm = new CStateMachine() {
	        public State idle = new State() {
	        	final Transition enterOnShape = new EnterOnShape("onShape") {
	        		public void action() {
	        			System.out.println("idle >> onShape");
	        			rect.setStroke(new BasicStroke(3));
	        		}
	        	};
	        };
	        
	        public State onShape = new State() {
	        	final Transition pressOnShape = new PressOnShape("pressedShape") {
	                public void action() {
	                	System.out.println("onShape >> pressedShape");
	                	rect.setStroke(new BasicStroke(2));
	                    rect.setFillPaint(Color.YELLOW);
	                }
	            };
	            
	            final Transition leaveShape = new LeaveOnShape("idle") {
	                public void action() {
	                	System.out.println("onShape >> idle");
	                	rect.setStroke(new BasicStroke(2));
	                	rect.setFillPaint(Color.WHITE);
	                }
	            };
	            
	        };
	        
	        public State pressedShape = new State() {
	            final Transition leaveOnShape = new LeaveOnShape("inactive") {
	                public void action() {
	                	System.out.println("pressedShape >> inactive");
	                }
	            };
	            
	            final Transition releaseOnShape = new ReleaseOnShape("onShape") {
	            	public void action() {
	            		System.out.println("pressedShape >> onShape");
	            		rect.setStroke(new BasicStroke(2));
	                	rect.setFillPaint(Color.WHITE);
	            	}
	            };
	            
	            final Transition outOfShape = new ReleaseOnShape("inactive") {
	            	public void action() {
	            		System.out.println("pressedShape >> inactive");
	            		rect.setStroke(new BasicStroke(2));
	                	rect.setFillPaint(Color.WHITE);
	            	}
	            };
	        };
	        
	        public State inactive = new State() {
	        	
	        	final Transition reenterOnShape = new EnterOnShape("pressedShape") {
	        		public void action() {
	        			System.out.println("inactive >> pressedShape");
	                	rect.setStroke(new BasicStroke(2));
	                	rect.setFillPaint(Color.WHITE);
	        		}
	        	};
	        	
	        	final Transition releaseOutOfShape = new Release("idle") {
	        		public void action() {
	        			System.out.println("inactive >> idle");
	                	rect.setStroke(new BasicStroke(2));
	                	rect.setFillPaint(Color.WHITE);
	        		}
	        	};
	        	
	        };
	    };
	    sm.attachTo(canvas);
	    sm.armTimer(500, false);
	    
	    final TimeOut test = new TimeOut("pressedShape");
    }

    public void action() {
	   System.out.println("ACTION!");
    }

    public CExtensionalTag getShape() {
	   return selected;
    }
    
    public CStateMachine getSm() {
		return sm;
	}

    static public void main(String[] args) {
	   JFrame frame = new JFrame();
	   JFrame frame2 = new JFrame();
	   
	   Canvas canvas = new Canvas(400, 400);
	   frame.getContentPane().add(canvas);
	   frame.setVisible(true);
	   frame2.setVisible(true);

	   SimpleButton simple = new SimpleButton(canvas, "simple");
	   frame2.getContentPane().add(new StateMachineVisualization(simple.getSm()));
	   frame.pack();
	   frame2.pack();
	   simple.getShape().translateBy(100,100);
	   
	   
    }

}
