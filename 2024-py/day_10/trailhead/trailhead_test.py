import os

import pytest

from day_10.trailhead.trailhead import Trailhead
from helper.file_utils import file_to_line_arr

trailhead_score_data = [
    ('../data/example.txt', 2),
    ('../data/example-2.txt', 4),
    ('../data/example-3.txt', 3),
    ('../data/example-4.txt', 36),
]

trailhead_rating_data = [
    ('../data/example-rating.txt', 3),
    ('../data/example-2.txt', 13),
    ('../data/example-rating-2.txt', 227),
    ('../data/example-4.txt', 81),
]

@pytest.mark.parametrize("file, expected", trailhead_score_data)
def test_get_trailhead_score(file, expected):
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), file))
	grid = file_to_line_arr(file_path, True)

	th = Trailhead(grid)

	assert th.trailhead_score() == expected

@pytest.mark.parametrize("file, expected", trailhead_rating_data)
def test_get_trailhead_rating(file, expected):
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), file))
	grid = file_to_line_arr(file_path, True)

	th = Trailhead(grid)

	assert th.trailhead_rating() == expected

def test_get_trailhead_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	grid = file_to_line_arr(file_path, True)

	th = Trailhead(grid)

	print("Part 1:", th.trailhead_score())
	print("Part 2:", th.trailhead_rating())

	assert th.trailhead_score() == 459
