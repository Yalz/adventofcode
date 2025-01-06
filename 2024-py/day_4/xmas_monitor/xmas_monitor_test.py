from ..xmas_monitor.xmas_monitor import *


def test_find_word():
    grid = [
        "....XXMAS.",
        ".SAMXMS...",
        "...S..A...",
        "..A.A.MS.X",
        "MASAMX.MM",
        ".....XA.A",
        ".S.S.S.SS",
        ".A.A.A.A.A",
        "..M.M.M.MM",
        ".X.X.XMASX"
    ]

    result = find_word(grid, "XMAS")

    assert 18 == result

def test_find_sandbox():
    grid = [
        "123",
        "456",
        "789",
    ]

    result = find_word(grid, "XMAS")

    assert 18 == result