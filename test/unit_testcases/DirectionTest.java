package unit_testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.mcgill.cs.stg.jetuml.framework.Direction;

@RunWith(value = Parameterized.class)
public class DirectionTest {
	
	int mNumber1;
	int mNumber2;
	
	int mAngle;
	
	public DirectionTest(int numberA, int numberB, int prAngle) {
		mNumber1 = numberA;
		mNumber2 = numberB;
		mAngle = prAngle;
	}
	
	@Test
	public void directionNegate(){
		try {
			Direction lDirc = new Direction(mNumber1, mNumber2);
			
			fail("Should throw an exception if one or more of given numbers are negative");
		}
		catch (ArithmeticException e) {
			assert (e.getMessage().length() != 0);//WE CAN USE ASSERT THAT
		}
	}
	
	//negated conditional → SURVIVED
	//Replaced double multiplication with division → SURVIVED
	@Test(expected = IllegalArgumentException.class)
	public void directionTurn() {

			Direction lDirc = new Direction(mNumber1, mNumber2);
			lDirc.turn(mAngle);
			double ls = lDirc.getX();
			
			//BECAUSE OF angle 30
			assertTrue((mNumber1 + lDirc.getX()) > mNumber1);
		assertTrue((mNumber2 - lDirc.getY()) < mNumber2);
			//BECAUSE OF angle 210
			lDirc.turn(210);
			ls = lDirc.getX();
			assertTrue((mNumber1 - lDirc.getX()) < mNumber1);
		assertTrue((mNumber2 + lDirc.getY()) > mNumber2);
		
	}
	
	
	@Parameters
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
		        { 16, 15, 30 }, { 16, 3, 30 }, { 2, 15, 30 }, { 22, 27, 30 }, { 85, 52, 30 }, { 43, 56, 30 },
		        { 57, 45, 37 }
        });
    }
}
