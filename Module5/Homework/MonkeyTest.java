import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * These tests are undercommented; I've included text from the assignment just
 * as a note to myself when writing the tests.
 * 
 * Because Monkey relies on randomness, there's very little testing that can be
 * done on the correctness of the getRandom() method. There are tests for the
 * negative cases, and then two sanity tests for rule use to make sure there is
 * some repetition going on.
 * 
 * I'd welcome any additional tests you add to this to test your own code (they
 * may be provided to next year's students to help them complete this assignment
 * correctly).
 * 
 * @author agarripoli
 * @version 2018-02-03 initial version
 */

public class MonkeyTest {

	/*
	 * Constructor
	 * 
	 * This method will be passed a grammar as a list of strings. Each string is one
	 * BNF rule. You should use regular expressions (see below) to break apart the
	 * rules and store them into a Map so that you can look up parts of the grammar
	 * efficiently later. You should not modify the list passed in. You should throw
	 * an IllegalArgumentException if grammar is null or an empty list.
	 * 
	 * If the grammar contains more than one line for the same non-terminal, the
	 * rule in the grammar for that non-terminal should be the concatenation of all
	 * of them. For example, if a grammar has a line "s ::= sally|bob" and then
	 * later has the line "s ::= sam|jim", your class should treat it the same as if
	 * it originally got the rule "s ::= sally|bob|sam|jim".
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMonkeyNull() {
		Monkey monkey = new Monkey(null);
		fail("expected exception not thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMonkeyEmptyList() {
		Monkey monkey = new Monkey(Collections.EMPTY_LIST);
		fail("expected exception not thrown");
	}

	@Test
	public void testMonkeySimpleGrammar() {
		Monkey monkey = new Monkey(getRules());
		assertTrue(monkey.isNonTerminal("exp")); // look for the last one in
	}

	private List<String> getRules() {
		List<String> rules = new LinkedList<>();
		rules.add("num::=1|2|3|4");
		rules.add("num::=5|6|7|8|9");
		rules.add("op ::=  +|  -   |*|/");
		rules.add("exp::=num|exp op num");
		return rules;
	}

	/*
	 * isNonTerminal
	 * 
	 * Returns true if the given symbol is a non-terminal of the grammar; returns
	 * false otherwise. You should throw an IllegalArgumentException if the string
	 * is null or has length 0.
	 * 
	 * For example, for the grammar above, isNonTerminal("sentence") would return
	 * true and isNonTerminal("swim") or isNonTerminal("boy") would return false.
	 * Note swim is not used in the language, and boy is used as a terminal.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsNonTerminalNull() {
		Monkey monkey = new Monkey(getRules());
		boolean result = monkey.isNonTerminal(null);
		fail("expected exception not thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsNonTerminalEmptyString() {
		Monkey monkey = new Monkey(getRules());
		boolean result = monkey.isNonTerminal("");
		fail("expected exception not thrown");
	}

	@Test
	public void testIsNonTerminalTerminal() {
		Monkey monkey = new Monkey(getRules());
		assertFalse(monkey.isNonTerminal("+"));
	}

	@Test
	public void testIsNonTerminalNotThere() {
		Monkey monkey = new Monkey(getRules());
		assertFalse(monkey.isNonTerminal("bob"));
	}

	@Test
	public void testIsNonTerminalFirst() {
		Monkey monkey = new Monkey(getRules());
		assertTrue(monkey.isNonTerminal("num"));
	}

	@Test
	public void testIsNonTerminalMiddle() {
		Monkey monkey = new Monkey(getRules());
		assertTrue(monkey.isNonTerminal("op"));
	}
	// already tests last one in constructor test.

	/*
	 * toString
	 * 
	 * This method should return a string representation of all non-terminal symbols
	 * from the grammar in alphabetical order. You will want to use the keySet of
	 * your map.
	 */
	@Test
	public void testToString() {
		Monkey monkey = new Monkey(getRules());
		String result = monkey.toString();
		assertEquals("[exp, num, op]", result);
	}

	/*
	 * This method generates a transformation of the given non-terminal into a
	 * string containing only terminals and returns it.
	 * 
	 * If nonterminal is not a non-terminal in your grammar or it is null, you
	 * should throw an IllegalArgumentException.
	 * 
	 * If nonterminal is a non-terminal in your grammar, you should apply one of its
	 * rules (each with equal probability). Use the Random class in java.util to
	 * help you make random choices between rules. Do not use Math.random(). You
	 * must keep applying rules until you have a string containing only
	 * terminals.`More on this below.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetRandomNull() {
		Monkey monkey = new Monkey(getRules());
		monkey.getRandom(null);
		fail("expected exception not thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetRandomEmptyString() {
		Monkey monkey = new Monkey(getRules());
		monkey.getRandom("");
		fail("expected exception not thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetRandomTerminalString() {
		Monkey monkey = new Monkey(getRules());
		monkey.getRandom("+");
		fail("expected exception not thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetRandomOtherString() {
		Monkey monkey = new Monkey(getRules());
		monkey.getRandom("bob");
		fail("expected exception not thrown");
	}

	@Test
	public void testGetRandomCombinedNums() {
		Monkey monkey = new Monkey(getRules());

		// it's random so we can't control it, but we can make sure
		// all of the numbers are eventually returned...this may take a while.
		// try 500 times, if it fails, tell the tester to check it out.
		int numbers[] = new int[10]; // 0 through 9, we won't use 0.
		for (int i = 0; i < 500; i++) {
			String result = monkey.getRandom("num");
			int val = Integer.parseInt(result.trim());
			numbers[val]++;
		}
		System.out.println("Results of 500 tries: " + Arrays.toString(numbers));
		for (int i = 1; i < 10; i++) {
			assertTrue("did not detect number " + i, numbers[i] > 0);
		}
	}

	@Test
	public void testGetRandomExpressions() {
		Monkey monkey = new Monkey(getRules());
		int maxops = 0;
		String longestExp = "";

		// it's random so we can't control it, but we can make sure
		// we try a bunch and only get things that look valid using REs
		for (int i = 0; i < 500; i++) {
			String result = monkey.getRandom("exp");
			String[] exp = result.split("[\\+\\-\\*/]"); // pick out all the operations
			// should only have numbers 1-9 left (as strings)
			// NOTE does not validate spaces are correctly done!!
			// NOTE does not ensure all rules are always done ...
			for (int j = 0; j < exp.length; j++) {
				assertEquals(1, exp[j].trim().length()); // all 1-character long minus spaces
				int num = Integer.parseInt(exp[j].trim());
				assertTrue("number needs to be 1-9", num > 0 && num < 10);
			}
			if (exp.length - 1 > maxops) {
				maxops = exp.length - 1;
				longestExp = result;
			}
		}
		System.out.println("longest expression: " + longestExp);
		assertTrue("really should get something 2 ops or longer in 500 tries", maxops > 2);
	}

}
