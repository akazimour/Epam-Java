package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;


public class StudentGradebookImpl implements StudentGradebook {

	private Map<Student, Map<String, BigDecimal>> map;
	private Comparator<Student> studentComparator;

	public StudentGradebookImpl() {
		map = new TreeMap<>(studentComparator = new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			if (o1 == null || o2 == null){
				throw new NullPointerException();
			}
			int i = o1.getFirstName().compareTo(o2.getFirstName());
			return i == 0 ? o1.getLastName().compareTo(o2.getLastName()) : i;
		}
	});
	}

	@Override
	public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
		if (student == null || discipline == null || grade == null){
			return false;
		}
		Map<String, BigDecimal> internal;

		Set<Map.Entry<Student, Map<String, BigDecimal>>> entries = map.entrySet();
		if (map.containsKey(student)){
			for (var e : entries) {
				Student key = e.getKey();
				int compare = studentComparator.compare(key, student);
				if (compare == 0) {
					Map<String, BigDecimal> value = e.getValue();
					internal = new HashMap<>(value);
					int beforeSize = internal.size();
					internal.put(discipline, grade);
					int afterSize = internal.size();
					map.put(student, internal);
					if (afterSize==beforeSize){
						return false;
					}
				}
			}
			}else {
			map.put(student, Map.of(discipline, grade));
		}

		Map<String, BigDecimal> stringBigDecimalMap = map.get(student);


		return true;

	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Comparator<Student> getComparator() {
		return studentComparator;
	}

	@Override
	public List<String> getStudentsByDiscipline(String discipline) {
		if (discipline == null){
			throw new NullPointerException();
		}
		List<String> toReturn = new ArrayList<>();
		//Gets a list of strings with students' names and their grades for the specified disciplines in the format "first_last name : grade"
		Set<Map.Entry<Student, Map<String, BigDecimal>>> entries = map.entrySet();
		for (var e : entries){
			Map<String, BigDecimal> value = e.getValue();
			if (value.containsKey(discipline)){
				Student student = e.getKey();
				BigDecimal bigDecimal = value.get(discipline);
				String dec = String.valueOf(bigDecimal);
				String toAdd = student.getLastName() + "_" + student.getFirstName() + ": " + dec;
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}

	@Override
	public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
		if (grade.equals(BigDecimal.ZERO)){
			throw new NullPointerException();
		}
		Set<Student> students = new HashSet<>();
		Map<String, BigDecimal> collect;
		Map<Student, Map<String, BigDecimal>> toReturn = new HashMap<>();
		Set<Map.Entry<Student, Map<String, BigDecimal>>> entries = map.entrySet();
		for (var e : entries){
			Student student = e.getKey();
			Map<String, BigDecimal> value = e.getValue();
			Collection<BigDecimal> values = value.values();
			for ( var v : values){
				int i = v.compareTo(grade);
if (i<0){
	students.add(student);
}
			}
		}
		for (var s : students){
			Iterator<Map.Entry<Student, Map<String, BigDecimal>>> iterator = entries.iterator();
			while (iterator.hasNext()){
				Map.Entry<Student, Map<String, BigDecimal>> next = iterator.next();
				int compare = studentComparator.compare(s, next.getKey());
				if (compare == 0){
					iterator.remove();
				}
			}
			Map<String, BigDecimal> stringBigDecimalMap = map.get(s);
			toReturn.put(s,stringBigDecimalMap);
		}

			return toReturn;
	}

	@Override
	public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
		if (map == null) {
			throw new NullPointerException();
		}
		Set<Student> students = map.keySet();
		List<Student> studentList = new ArrayList<>(students);
		studentList.sort(studentComparator);
		Map<BigDecimal, List<Student>> returnList = new HashMap<>();
		List<Student> temp;
		double sum = 0;
		BigDecimal numBigDecimal = null;
		List<BigDecimal> bgList = new ArrayList<>();
		for (var s : studentList) {
			Map<String, BigDecimal> stringBigDecimalMap = map.get(s);
			Collection<BigDecimal> values = stringBigDecimalMap.values();
			for (var v : values) {
				sum = sum + v.doubleValue();
			}
			double doubleAvg = sum / values.size();
			numBigDecimal = new BigDecimal(doubleAvg, MathContext.DECIMAL64);
			numBigDecimal = numBigDecimal.setScale(1, RoundingMode.DOWN);
			Set<Map.Entry<BigDecimal, List<Student>>> entries = returnList.entrySet();
			if (returnList.containsKey(numBigDecimal)) {
				for (var v : entries) {
					List<Student> value = v.getValue();
					int i = numBigDecimal.compareTo(v.getKey());
					if (i==0){
						temp = new ArrayList<>(value);
						temp.add(s);
						temp.sort(studentComparator);
						returnList.put(numBigDecimal, temp);
						sum = 0;
					}
				}
			}else {
				returnList.put(numBigDecimal, List.of(s));
			}
			sum = 0;
		}
			return returnList;
		}

	}
