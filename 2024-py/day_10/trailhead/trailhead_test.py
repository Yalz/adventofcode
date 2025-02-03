import os

import pytest

from day_10.trailhead.trailhead import Trailhead
from helper.file_utils import file_to_line_arr

testdata = [
    ('../data/example.txt', 2),
    ('../data/example-2.txt', 4),
    ('../data/example-3.txt', 3),
    ('../data/example-4.txt', 36),
]

@pytest.mark.parametrize("file, expected", testdata)
def test_get_trailhead(file, expected):
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), file))
	grid = file_to_line_arr(file_path, True)

	th = Trailhead(grid)

	assert len(th.paths) == expected

def test_get_trailhead_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	grid = file_to_line_arr(file_path, True)

	th = Trailhead(grid)

	print("Part 1:", len(th.paths))