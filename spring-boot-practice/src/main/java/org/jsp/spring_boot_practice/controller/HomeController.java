package org.jsp.spring_boot_practice.controller;

import org.jsp.spring_boot_practice.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

	@PutMapping(value = "/test")
	public String test() {
		return "This API is created to test the Spring Boot Application";
	}

	@GetMapping(value = "/sum")
	public String getSum(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		return n1 + "+" + n2 + "=" + (n1 + n2);
	}

	@GetMapping(value = "/diff")
	public String getDifference(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		return n1 + "-" + n2 + "=" + (n1 - n2);
	}

	@GetMapping("/even-or-odd/{n}")
	public String evenOrOdd(@PathVariable(name = "n") int num) {
		return num % 2 == 0 ? num + " is an even number " : num + " is an odd number";
	}

	@GetMapping(value = "/product")
	public String getProduct(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		return n1 + "*" + n2 + "=" + (n1 * n2);
	}

	@GetMapping(value = "/quotient")
	public String getQuotient(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		return n1 + "/" + n2 + "=" + (n1 / n2);
	}

	@GetMapping(value = "/remainder")
	public String getRemainder(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		return n1 + "%" + n2 + "=" + (n1 % n2);
	}

	@GetMapping(value = "/largest")
	public String getLargest(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		if (n1 > n2) {
			return n1 + " is largest";
		} else {
			return n2 + " is largest";
		}

	}

	@GetMapping(value = "/smallest")
	public String getSmallest(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		if (n1 < n2) {
			return n1 + " is smallest";
		} else {
			return n2 + " is smallest";
		}

	}

	@GetMapping(value = "/hcf")
	public int getHcf(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		if (n2 == 0)
			return n1;
		return getHcf(n2, n1 % n2);
	}

	@GetMapping(value = "/lcm")
	public int getLcm(@RequestParam(name = "n1") int n1, @RequestParam(name = "n2") int n2) {
		return (n1 / getHcf(n1, n2)) * n2;
	}

	@GetMapping("/prime/{n}")
	public boolean isPrime(@PathVariable(name = "n") int num) {
		int count = 0;

		for (int i = 1; i <= num; i++) {
			if (num % i == 0) {
				count++;
			}
		}
		return count == 2;
	}

	@GetMapping("/armstrong/{n}")
	public boolean isArmstrong(@PathVariable(name = "n") String num) {
		char[] s = num.toCharArray();
		int size = s.length;
		int sum = 0;

		for (char number : s) {
			int temp = 1;
			int i = Integer.parseInt(Character.toString(number));

			for (int j = 0; j <= size - 1; j++) {
				temp *= i;
			}
			sum += temp;
		}

		if (sum == Integer.parseInt(num)) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/getSum/{n}")
	public int getSum(@PathVariable(name = "n") int num) {
		int sum = 0;

		while (num != 0) {
			sum = sum + num % 10;
			num = num / 10;
		}

		return sum;
	}

	@GetMapping("/getCount/{n}")
	public int getCount(@PathVariable(name = "n") int num) {
		if (num == 0)
			return 1;

		int count = 0;

		// Remove last digit from number
		// till number is 0
		while (num != 0) {

			// Increment count
			count++;
			num /= 10;
		}

		// return the count of digit
		return count;
	}

	@GetMapping("/getReverse/{n}")
	public int getReverse(@PathVariable(name = "n") int num) {
		int rev_num = 0;
		while (num > 0) {
			rev_num = rev_num * 10 + num % 10;
			num = num / 10;
		}
		return rev_num;
	}

	@GetMapping("/getBinary/{n}")
	public String getBinary(@PathVariable(name = "n") int num) {
		String bin = "";
		int rem = 0;

		while (num != 0) {
			rem = num % 2;
			bin = rem + bin;
			num = num / 2;
		}
		return bin;
	}

	@GetMapping("/vowels/{s}")
	public int countVowels(@PathVariable(name = "s") String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'a' || ch == 'e' || ch == 'i'
					|| ch == 'o' || ch == 'u') {
				count++;
			}
		}
		return count;
	}

	@GetMapping("/palindrome/{s}")
	public boolean isPlaindrome(@PathVariable(name = "s") String s) {
		// Initializing an empty string to store the reverse
		// of the original str
		String rev = "";

		// Initializing a new boolean variable for the
		// answer
		boolean ans = false;

		for (int i = s.length() - 1; i >= 0; i--) {
			rev = rev + s.charAt(i);
		}

		// Checking if both the strings are equal
		if (s.equals(rev)) {
			ans = true;
		}
		return ans;
	}

	@GetMapping("/consonent/{s}")
	public int countConsonent(@PathVariable(name = "s") String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch != 'A' || ch != 'E' || ch != 'I' || ch != 'O' || ch != 'U' || ch != 'a' || ch != 'e' || ch != 'i'
					|| ch != 'o' || ch != 'u') {
				count++;
			}
		}
		return count;

	}
	
	@PostMapping("/print")
	public String printUserDetails(@RequestBody(required = false) User user) {
		System.out.println(user);
		return "Printed User Details";
	}

}
