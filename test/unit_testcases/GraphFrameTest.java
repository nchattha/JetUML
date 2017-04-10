package unit_testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.cs.stg.jetuml.diagrams.ClassDiagramGraph;
import ca.mcgill.cs.stg.jetuml.framework.GraphFrame;
import ca.mcgill.cs.stg.jetuml.framework.GraphPanel;
import ca.mcgill.cs.stg.jetuml.graph.Graph;


public class GraphFrameTest {
	
	
	JTabbedPane mTabbedPane;
	private GraphFrame mGraphFrame;
	@Before
	public void setUp() throws Exception {
		mTabbedPane = new JTabbedPane();
		mGraphFrame = new GraphFrame((Graph) ClassDiagramGraph.class.newInstance(), mTabbedPane);

	}
	
	//TESTING THE TAB PANE IS VALID OR NOT
	@Test
	public void addGraphTest() throws InstantiationException, IllegalAccessException {
		
		
		assertTrue(mGraphFrame.getGraph().getDescription().contains("Class Diagram"));
		assertFalse(mGraphFrame.getGraph().getDescription().contains("Sequence Diagram"));
		JPanel centerWrapper = new JPanel( new CardLayout() );
        centerWrapper.setInheritsPopupMenu(true);
		mTabbedPane.setEnabled(true);
		assertTrue(mTabbedPane.getTabCount() == 0);
		
	}
	
	@Test
	public void addGraphPanelTest()
   	{
		mGraphFrame.getGraphPanel().setBounds(13, 24, 20, 30);
		mGraphFrame.setEnabled(true);
		mGraphFrame.getGraphPanel().setFocusable(true);
		GraphPanel lGraphPanel = mGraphFrame.getGraphPanel();
		assertTrue(lGraphPanel.isEnabled());
		assertTrue(lGraphPanel.getX() == 24);

	}
	
	@Test
	public void getJTabbedPane() {
		
		JTabbedPane lTabbedPane = mGraphFrame.getJTabbedPane();
		mTabbedPane.setBounds(13, 30, 40, 50);
		mTabbedPane.setEnabled(true);
		mGraphFrame.add(mTabbedPane).addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				assertTrue(e.isActionKey());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				assertTrue(e.isActionKey());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				assertTrue(e.isActionKey());
			}
		});
		
		assertTrue(lTabbedPane.getHeight() != mTabbedPane.getHeight());
		
	}
}
