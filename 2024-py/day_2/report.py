import re
from termcolor import colored
from xmlrpc.client import Boolean

class Report:
	def __init__(self, file_location, tolerated_bad_levels=0):
		self.report_values = []
		self.tolerated_bad_levels = tolerated_bad_levels
		file = open(file_location, "r")

		while True:
			content = file.readline()
			if not content:
				break
			# Extract integers using regular expressions
			string_numbers = re.findall(r'\d+', content)

			# Convert the extracted strings to integers
			self.report_values.append([int(num) for num in string_numbers])

		file.close()

	def overview(self):
		print(self.report_values)

	def validate_level(self, row, debug) -> Boolean:
		previous_value = None
		previous_diff = None
		bad_levels = 0

		for v in row:
			if previous_value is not None:
				diff = v - previous_value
				if abs(diff) < 1 or abs(diff) > 3:
					if debug:
						print("diff for value ", v, " was ", diff)
					bad_levels += 1
					if self.tolerated_bad_levels < bad_levels:
						return False

				if previous_diff is not None:
					if (diff < 0 < previous_diff) or (diff > 0 > previous_diff):
						if debug:
							print("diff for value ", v, " was ", diff, " and previous diff was ", previous_diff)
						bad_levels += 1
						if self.tolerated_bad_levels < bad_levels:
							return False
				previous_diff = diff
			previous_value = v
		return True

	def validate_report(self, debug):
		valid_reports = 0
		for v in self.report_values:
			report_valid = self.validate_level(v, debug)
			if report_valid:
				valid_reports += 1
			if debug:
				print(report_valid, v)
		return valid_reports
