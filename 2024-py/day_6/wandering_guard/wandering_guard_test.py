import os

from day_6.wandering_guard.wandering_guard import WanderingGuard
from helper.file_utils import file_to_line_arr


def test_wander():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)

    assert 41 == guard.wander()

def test_td():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/td-1.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)

    assert 15 == guard.wander()


def test_input():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)

    print("Part 1: ", guard.wander())
