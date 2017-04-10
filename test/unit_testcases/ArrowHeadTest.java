package unit_testcases;

import static org.junit.Assert.assertFalse;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.mcgill.cs.stg.jetuml.diagrams.ClassDiagramGraph;
import ca.mcgill.cs.stg.jetuml.framework.ArrowHead;
import ca.mcgill.cs.stg.jetuml.framework.Grid;
import ca.mcgill.cs.stg.jetuml.graph.AggregationEdge;
import ca.mcgill.cs.stg.jetuml.graph.ClassNode;
import ca.mcgill.cs.stg.jetuml.graph.DependencyEdge;
import ca.mcgill.cs.stg.jetuml.graph.Graph;

@RunWith(value = Parameterized.class)
public class ArrowHeadTest {
	
	static GeneralPath mGeneralPath;
	
	private Graph aGraph;
	
	private Grid aGrid;
	
	private Graphics2D aGraphics;
	
	private ClassNode aNode1;
	
	private ClassNode aNode2;
	
	private ClassNode aNode3;
	
	private DependencyEdge aEdge1;
	
	private AggregationEdge aEdge2;


	@BeforeClass
	public static void setUpClass() {
		//executed only once, before the first test
		mGeneralPath = new GeneralPath();
		mGeneralPath.moveTo(10, 10);
	}
	
	public ArrowHeadTest(int numberA, int numberB) {
		mGeneralPath.lineTo(numberA, numberB);
	}
	
	@Before
	public void setup() {
		aGraph = new ClassDiagramGraph();
		aGrid = new Grid();
		aGraphics = new BufferedImage(112, 221, BufferedImage.TYPE_INT_RGB).createGraphics();
		aNode1 = new ClassNode();
		aNode2 = new ClassNode();
		aNode3 = new ClassNode();
		aEdge1 = new DependencyEdge();
		aEdge2 = new AggregationEdge(AggregationEdge.Type.Composition);
		aGraph.insertNode(aNode1);
		aGraph.insertNode(aNode2);
		aGraph.insertNode(aNode3);
	}
	

	
	@Test
	public void drawTest() {
		
		
		mGeneralPath.closePath();
	
		assert (mGeneralPath.contains(22, 27));
		
		aGraph.restoreEdge(aEdge1, aNode1, aNode2);
		aGraph.restoreEdge(aEdge2, aNode1, aNode2);
		
		ArrowHead.BLACK_TRIANGLE.draw(aGraphics, new Point2D.Double(20, 40), new Point2D.Double(40, 40));
		GeneralPath lPath = ArrowHead.BLACK_TRIANGLE.getPath(new Point2D.Double(20, 10), new Point2D.Double(40, 10));
		assertFalse(lPath.toString().contains("BLACK_TRIANGLE"));
		
	}
	
	@Parameters
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {16, 15},
                {22, 27},
                {85, 52},
                {43, 56},
                {57, 45}
        });
    }
	
	
}
