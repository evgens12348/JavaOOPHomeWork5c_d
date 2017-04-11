package com.gmail.s12348.evgen;

import java.io.File;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Group group = new Group();
		File fileOne = new File("Group.txt");
		if (fileOne.exists() == true) {
			group.readToFile();
		}

		group.enterStudent();

		int s = JOptionPane.showConfirmDialog(null, "Do I need to remove a student from the group?");
		if (s == 0) {
			group.requestDeleteStudent();
		}

		s = JOptionPane.showConfirmDialog(null, "Need to find a student on the list?");
		if (s == 0) {
			group.poiskStudent(group.zaprosPoiskStudent());
		}

		s = JOptionPane.showConfirmDialog(null, "Do I need to sort the group?");
		if (s == 0) {
			group.sortArray();
		}
		group.writeToFile();

		System.out.println(group.toString());
		System.out.println("The military commissar chose:");
		group.prizivnik();

	}

}
