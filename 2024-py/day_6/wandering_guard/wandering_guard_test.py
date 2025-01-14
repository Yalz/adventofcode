import os

from day_6.wandering_guard.wandering_guard import WanderingGuard
from helper.file_utils import file_to_line_arr


def test_wander():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)
    guard.wander()

    assert guard.steps_taken() == 41

def test_alternate_routes():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)
    guard.wander()

    assert guard.alternate_routes == 6

def test_td():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/td-1.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)
    guard.wander()

    assert guard.steps_taken() == 15

def test_input():
    file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
    grid = file_to_line_arr(file_path, True)

    guard = WanderingGuard(grid)
    guard.wander()

    print("Part 1: ", guard.steps_taken())
    print("Part 2: ", guard.alternate_routes)

