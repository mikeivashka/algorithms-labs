import datetime as dt

import pandas as pd
import plotly.express as px
from pandas import *

matrix = [
    [1, 4, 3, 1, 3, 1, 2, 3, 2, 1, 4, 3],
    [3, 2, 4, 3, 4, 3, 2, 1, 2, 2, 2, 1],
    [1, 2, 4, 2, 4, 2, 4, 2, 3, 4, 2, 1],
    [3, 2, 4, 1, 3, 3, 1, 2, 4, 2, 3, 1],

    [3, 1, 2, 3, 2, 1, 4, 3, 0, 0, 0, 0],
    [4, 3, 2, 1, 2, 2, 2, 1, 0, 0, 0, 0],
    [4, 2, 4, 2, 3, 4, 2, 1, 0, 0, 0, 0],
    [3, 3, 1, 2, 4, 2, 3, 1, 0, 0, 0, 0],

    [2, 1, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0],
    [2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0],
    [3, 4, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0],
    [4, 2, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0],
]


def critical(matrix):
    i = j = 0
    time = matrix[0][0]

    while i < len(matrix) - 1:
        while j < len(matrix) - 1:
            if matrix[i][j + 1] > matrix[i + 1][j]:
                j += 1
                time += matrix[i][j]
                continue
            else:
                time += matrix[i + 1][j]
                break
        i += 1
    return time


print(DataFrame(matrix))
print(f"Время выполнения: {critical(matrix)}")

asyncMode = [

    # the first process

    dict(
        process=1,
        block=1,
        duration=dt.datetime.fromtimestamp(1).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(0).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=2,
        duration=dt.datetime.fromtimestamp(5).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(1).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=3,
        duration=dt.datetime.fromtimestamp(8).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(5).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=4,
        duration=dt.datetime.fromtimestamp(9).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(8).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ####

    dict(
        process=1,
        block=1,
        duration=dt.datetime.fromtimestamp(26).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(23).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=2,
        duration=dt.datetime.fromtimestamp(27).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(26).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=3,
        duration=dt.datetime.fromtimestamp(29).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(27).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=4,
        duration=dt.datetime.fromtimestamp(32).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(29).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ####

    dict(
        process=1,
        block=1,
        duration=dt.datetime.fromtimestamp(46).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(44).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=2,
        duration=dt.datetime.fromtimestamp(47).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(46).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=3,
        duration=dt.datetime.fromtimestamp(51).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(47).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=1,
        block=4,
        duration=dt.datetime.fromtimestamp(54).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(51).strftime('%Y-%m-%d %H:%M:%S')
    ),

    # the second process

    dict(
        process=2,
        block=1,
        duration=dt.datetime.fromtimestamp(4).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(1).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=2,
        duration=dt.datetime.fromtimestamp(7).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(5).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=3,
        duration=dt.datetime.fromtimestamp(12).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(8).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=4,
        duration=dt.datetime.fromtimestamp(14).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(12).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ###

    dict(
        process=2,
        block=1,
        duration=dt.datetime.fromtimestamp(30).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(26).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=2,
        duration=dt.datetime.fromtimestamp(33).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(30).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=3,
        duration=dt.datetime.fromtimestamp(35).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(33).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=4,
        duration=dt.datetime.fromtimestamp(36).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(35).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ###

    dict(
        process=2,
        block=1,
        duration=dt.datetime.fromtimestamp(48).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(46).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=2,
        duration=dt.datetime.fromtimestamp(50).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(48).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=3,
        duration=dt.datetime.fromtimestamp(53).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(51).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=2,
        block=4,
        duration=dt.datetime.fromtimestamp(55).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(54).strftime('%Y-%m-%d %H:%M:%S')
    ),

    # the third process

    dict(
        process=3,
        block=1,
        duration=dt.datetime.fromtimestamp(5).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(4).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=2,
        duration=dt.datetime.fromtimestamp(9).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(7).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=3,
        duration=dt.datetime.fromtimestamp(16).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(12).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=4,
        duration=dt.datetime.fromtimestamp(18).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(16).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ###

    dict(
        process=3,
        block=1,
        duration=dt.datetime.fromtimestamp(34).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(30).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=2,
        duration=dt.datetime.fromtimestamp(36).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(34).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=3,
        duration=dt.datetime.fromtimestamp(40).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(36).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=4,
        duration=dt.datetime.fromtimestamp(42).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(40).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ###

    dict(
        process=3,
        block=1,
        duration=dt.datetime.fromtimestamp(51).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(48).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=2,
        duration=dt.datetime.fromtimestamp(55).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(51).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=3,
        duration=dt.datetime.fromtimestamp(57).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(55).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=3,
        block=4,
        duration=dt.datetime.fromtimestamp(58).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(57).strftime('%Y-%m-%d %H:%M:%S')
    ),

    # the fourth process

    dict(
        process=4,
        block=1,
        duration=dt.datetime.fromtimestamp(8).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(5).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=2,
        duration=dt.datetime.fromtimestamp(12).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(9).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=3,
        duration=dt.datetime.fromtimestamp(22).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(16).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=4,
        duration=dt.datetime.fromtimestamp(23).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(22).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ###

    dict(
        process=4,
        block=1,
        duration=dt.datetime.fromtimestamp(37).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(34).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=2,
        duration=dt.datetime.fromtimestamp(40).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(37).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=3,
        duration=dt.datetime.fromtimestamp(41).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(40).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=4,
        duration=dt.datetime.fromtimestamp(44).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(42).strftime('%Y-%m-%d %H:%M:%S')
    ),

    ###

    dict(
        process=4,
        block=1,
        duration=dt.datetime.fromtimestamp(55).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(51).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=2,
        duration=dt.datetime.fromtimestamp(57).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(55).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=3,
        duration=dt.datetime.fromtimestamp(60).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(57).strftime('%Y-%m-%d %H:%M:%S')
    ),
    dict(
        process=4,
        block=4,
        duration=dt.datetime.fromtimestamp(61).strftime('%Y-%m-%d %H:%M:%S'),
        start=dt.datetime.fromtimestamp(60).strftime('%Y-%m-%d %H:%M:%S')
    ),

]
c_df = pd.DataFrame(asyncMode)
c_fig = px.timeline(c_df, x_start="start", x_end="duration", y="process", color="block")
c_fig.show()
