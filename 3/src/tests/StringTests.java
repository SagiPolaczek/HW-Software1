package tests;
import il.ac.tau.cs.sw1.hw3.StringUtils ;

import org.junit.Assert;
import org.junit.Test;

public class StringTests {

	@Test
	public void TestfindSortedSequence() {
	//	System.out.println("not to".equals(StringUtils.findSortedSequence ("to be or not to be")));
		Assert.assertEquals(StringUtils.findSortedSequence ("to be or not to be"), "not to");
		Assert.assertEquals(StringUtils.findSortedSequence ("to be or not to be") , "not to");
		Assert.assertEquals(StringUtils.findSortedSequence ("my mind is an empty zoo") , "an empty zoo");
		Assert.assertEquals(StringUtils.findSortedSequence ("") , "");
		Assert.assertEquals(StringUtils.findSortedSequence ("andy bought candy") , "andy bought candy");
		Assert.assertEquals(StringUtils.findSortedSequence ("life is not not not fair") , "is not not not");
		Assert.assertEquals(StringUtils.findSortedSequence ("art act") , "act");
	}
	
	@Test
	public void TestisAnagram() {
		Assert.assertSame(StringUtils.isAnagram("mothEr in law","hitlEr woman") , true);
		Assert.assertSame(StringUtils.isAnagram("ListeN","SileNt") , true);
		Assert.assertSame(StringUtils.isAnagram("software","jeans") , false);
		Assert.assertSame(StringUtils.isAnagram("Funeral","real Fun") , true);
		Assert.assertSame(StringUtils.isAnagram("Aa","aA") , true);
		Assert.assertSame(StringUtils.isAnagram(""," ") , true);

	}
	
	@Test
	public void TestisEditDistanceOne() {
		Assert.assertSame(StringUtils.isEditDistanceOne("dog","god"),false);
		Assert.assertSame(StringUtils.isEditDistanceOne("x","x"),true);
		Assert.assertSame(StringUtils.isEditDistanceOne("main","man"),true);
		Assert.assertSame(StringUtils.isEditDistanceOne("ab","cab"),true);
	}
	}

