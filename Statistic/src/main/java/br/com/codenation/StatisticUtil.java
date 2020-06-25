package br.com.codenation;

import java.security.InvalidParameterException;
import java.time.Clock;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticUtil {

	public static int average(int[] elements) throws NullPointerException {
		//OptionalDouble d = Arrays.stream(elements).average();
		//return (int)d.getAsDouble();

		if(elements == null || elements.length == 0)
			throw new NullPointerException("Array vazio ou nulo.");

		double sum = 0;

		for (int i = 0; i < elements.length; i++)
			sum += elements[i];

		return (int)Math.round(sum / elements.length);
	}

	public static int mode(int[] elements) throws NullPointerException {
		class KeyCount {
			public Integer key;
			public Integer count = 0;

			public Integer getCount(){
				return this.count;
			}

			public KeyCount(Integer key, Integer count){
				this.key = key;
				this.count = count;
			}
		}

		if(elements ==null || elements.length == 0)
			throw new NullPointerException("Array vazio ou nulo.");

		List<KeyCount> keyValueList = new ArrayList<>();
		Optional<KeyCount> key;

		for (int e : elements) {
			key = keyValueList.stream().filter(o -> o.key == e).findFirst();

			if(key.isPresent()){
				key.get().count++;
			} else {
				keyValueList.add(new KeyCount(e,1));
			}
		}

		List<KeyCount> sortedList = keyValueList.stream()
				.sorted(Comparator.comparing(KeyCount::getCount).reversed())
				.collect(Collectors.toList());

		Optional<KeyCount> first = sortedList.stream().findFirst();

		return first.get().key;
	}

	public static int median(int[] elements) throws NullPointerException {
		int median = 0;

		if(elements == null)
			throw new NullPointerException("Array nulo.");

		switch (elements.length) {
			case 0:
				median = 0; //throw RuntimeException
				break;
			case 1:
				median = elements[0];
				break;
			case 2:
				median = (elements[0] + elements[2]) / 2;
				break;
			default:
				Arrays.sort(elements);

				int metade = elements.length / 2;

				if (elements.length % 2 == 1) { // contagem ímpar
					metade = (int)Math.floor(metade); // arredonda pra baixo
					median = elements[metade];
				} else { // contagem par
					median = (int)((elements[metade - 1] + elements[metade]) / 2);
				}
		}

		return median;
	}
}