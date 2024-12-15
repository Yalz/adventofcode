import re

class Historian:

	def __init__(self, file_location):
		file = open(file_location, "r")
		self.left_col = []
		self.right_col = []

		while True:
			content = file.readline()
			if not content:
				break
			# Extract integers using regular expressions
			string_numbers = re.findall(r'\d+', content)

			# Convert the extracted strings to integers
			integer_list = [int(num) for num in string_numbers]
			self.left_col.append(integer_list[0])
			self.right_col.append(integer_list[1])

		file.close()

	def part1(self):
		self.left_col.sort()
		self.right_col.sort()

		output = 0

		for i in range(len(self.left_col)):
			output += abs(self.left_col[i] - self.right_col[i])

		return output

	def part2(self):
		output = 0

		for i in range(len(self.left_col)):
			output += abs(self.left_col[i] * self.right_col.count(self.left_col[i]))

		return output