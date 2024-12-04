import unittest
from report import Report


class MyTestCase(unittest.TestCase):

	def test_part1(self):
		report = Report("data/example.txt")
		self.assertEqual(2, report.validate_report(False))

	def test_part2(self):
		report = Report("data/example.txt", 1)
		self.assertEqual(4, report.validate_report(True))

if __name__ == '__main__':
	unittest.main()
