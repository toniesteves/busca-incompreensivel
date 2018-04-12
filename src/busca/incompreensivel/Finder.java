package busca.incompreensivel;

import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> listPerson;

	public Finder(List<Person> listPerson) {
		this.listPerson = listPerson;
	}

	public Match find(PersonType personType) {
		List<Match> listMatch = getListMatch();
		Match answer = getFirstMatch(listMatch);
		for (Match result : listMatch) {
			switch (personType) {
				case CLOSEST :
					if (result.ageDifference < answer.ageDifference) {
						answer = result;
					}
					break;

				case FURTHEST :
					if (result.ageDifference > answer.ageDifference) {
						answer = result;
					}
					break;
			}
		}
		return answer;
	}

	private Match getFirstMatch(List<Match> listMatch) {
		return listMatch.iterator().hasNext() ? listMatch.iterator().next() : new Match();
	}

	private List<Match> getListMatch() {
		List<Match> listMatch = new ArrayList<Match>();
		for (int i = 0; i < listPerson.size() - 1; i++) {
			for (int j = i + 1; j < listPerson.size(); j++) {
				Match match = new Match();
				if (listPerson.get(i).birthDate.getTime() < listPerson.get(j).birthDate.getTime()) {
					match.personOne = listPerson.get(i);
					match.personTwo = listPerson.get(j);
				} else {
					match.personOne = listPerson.get(j);
					match.personTwo = listPerson.get(i);
				}
				match.ageDifference = match.personTwo.birthDate.getTime() - match.personOne.birthDate.getTime();
				listMatch.add(match);
			}
		}
		return listMatch;
	}
}
