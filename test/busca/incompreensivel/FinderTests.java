package busca.incompreensivel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FinderTests {

	Person sue;
	Person greg;
	Person sarah;
	Person mike;
	Person toni;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		toni = new Person("Toni", new Date(48, 0, 1));
		sue = new Person("Sue", new Date(50, 0, 1));
		greg = new Person("Greg", new Date(52, 5, 1));
		sarah = new Person("Sarah", new Date(82, 0, 1));
		mike = new Person("Mike", new Date(79, 0, 1));
	}

	@Test
	public void returnsEmptyResultsWhenGivenEmptyList() {
		List<Person> list = new ArrayList<Person>();
		Finder finder = new Finder(list);
		Match result = finder.find(PersonType.CLOSEST);
		assertEquals(null, result.personOne);
		assertEquals(null, result.personTwo);
	}

	@Test
	public void returnsEmptyResultsWhenGivenOnePerson() {
		List<Person> list = new ArrayList<Person>();
		list.add(sue);

		Finder finder = new Finder(list);

		Match result = finder.find(PersonType.CLOSEST);

		assertEquals(null, result.personOne);
		assertEquals(null, result.personTwo);
	}

	@Test
	public void returnsClosestTwoForTwoPeople() {
		List<Person> list = new ArrayList<Person>();
		list.add(sue);
		list.add(greg);
		Finder finder = new Finder(list);

		Match result = finder.find(PersonType.CLOSEST);

		assertEquals(sue, result.personOne);
		assertEquals(greg, result.personTwo);
	}

	@Test
	public void returnsFurthestTwoForTwoPeople() {
		List<Person> list = new ArrayList<Person>();
		list.add(mike);
		list.add(greg);

		Finder finder = new Finder(list);

		Match result = finder.find(PersonType.FURTHEST);

		assertEquals(greg, result.personOne);
		assertEquals(mike, result.personTwo);
	}

	@Test
	public void returnsFurthestTwoForFourPeople() {
		List<Person> list = new ArrayList<Person>();
		list.add(sue);
		list.add(sarah);
		list.add(mike);
		list.add(greg);
		list.add(toni);
		Finder finder = new Finder(list);

		Match result = finder.find(PersonType.FURTHEST);

		assertEquals(toni, result.personOne);
		assertEquals(sarah, result.personTwo);
	}

	@Test
	public void returnsClosestTwoForFourPeople() {
		List<Person> list = new ArrayList<Person>();
		list.add(sue);
		list.add(sarah);
		list.add(mike);
		list.add(greg);
		list.add(toni);

		Finder finder = new Finder(list);
		Match result = finder.find(PersonType.CLOSEST);
		assertEquals(toni, result.personOne);
		assertEquals(sue, result.personTwo);
	}

}
