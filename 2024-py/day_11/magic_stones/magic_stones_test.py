import os

import helper.file_utils
from day_11.magic_stones.magicstones import MagicStones


def test_roll_basic():
	stones = MagicStones('0 1 10 99 999')

	stones.roll()
	assert stones.hand() == '1 2024 1 0 9 9 2021976'

def test_roll_advanced():
	stones = MagicStones('125 17')

	stones.roll()
	assert stones.hand() == '253000 1 7'
	stones.roll()
	assert stones.hand() == '253 0 2024 14168'
	stones.roll()
	assert stones.hand() == '512072 1 20 24 28676032'
	stones.roll()
	assert stones.hand() == '512 72 2024 2 0 2 4 2867 6032'
	stones.roll()
	assert stones.hand() == '1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32'
	stones.roll()
	assert stones.hand() == '2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2'

	assert stones.stone_count() == 22

	for i in range(19):
		stones.roll()
	assert stones.stone_count() == 55312

def test_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	stones = MagicStones(helper.file_utils.file_to_str(file_path))

	for i in range(25):
		stones.roll()
		print("Roll :", i, "Stone count:", stones.stone_count())



	print("Part 1:", stones.stone_count())

	# for i in range(25, 75):
	# 	stones.roll()
	# 	print("Roll :", i, "Stone count:", stones.stone_count())
	#
	# print("Part 2:", stones.stone_count())