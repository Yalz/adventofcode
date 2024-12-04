import unittest
import historian


class HistorianTests(unittest.TestCase):
	hist = historian.Historian("data/example.txt")

	def test_part1(self):
		self.assertEqual(11, self.hist.part1())


	def test_part2(self):
		self.assertEqual(31, self.hist.part2())


if __name__ == '__main__':
	unittest.main()