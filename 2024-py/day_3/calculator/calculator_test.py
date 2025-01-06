import pytest

from ..calculator.calculator import *


@pytest.mark.parametrize("line, valid",
                         [
                             ("mul(44,46)", True),
                             ("mul(4*", False),
                             ("mul(6,9!", False),
                             ("?(12,34)", False),
                             ("mul ( 2 , 4 )", False),
                         ], )
def test_contains_valid_mul(line, valid):
    x = parse_valid_commands(line)
    assert (len(x) > 0) == valid


def test_count_valid_mul():
    line = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    commands = parse_valid_commands(line)
    assert len(commands) == 4


def test_process_commands():
    line = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    assert 161 == process_commands(line)


def test_parse_conditionals():
    line = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5)"
    conditionals = parse_conditionals(line)
    assert ['xmul(2,4)&mul[3,7]!^', '?mul(8,5)'] == conditionals


def test_parse_conditionals_edge_case_1():
    line = "!mul(701,946)~#^<don't())mul(982,103)?how()* ^from(507,869)'who()select()mul(107,848)why() ')when()when()select(903,770)"
    assert "!mul(701,946)~#^<" == filter_instructions(line)


def test_process_commands_conditional():
    line = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5)"
    assert 48 == process_commands(line, True)
