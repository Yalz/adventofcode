import re

import pytest

from day_2.report.report import Report, validate_level

example_path = "../data/example.txt"


def test_part1():
    report = Report(example_path)
    assert (2 == report.validate_report(True))


def test_part2():
    report = Report(example_path, True)
    assert (4 == report.validate_report(True))


@pytest.mark.parametrize("row, tolerate, valid",
                         [
                             ("7 6 4 2 1", True, True),
                             ("1 2 7 8 9", True, False),
                             ("9 7 6 2 1", True, False),
                             ("1 3 2 4 5", True, True),
                             ("8 6 4 4 1", True, True),
                             ("1 3 6 7 9", True, True),
                         ], )
def test_validate_level(row, tolerate, valid):
    input_row = [int(num) for num in re.findall(r'\d+', row)]
    assert valid == validate_level(input_row, True, tolerate)
