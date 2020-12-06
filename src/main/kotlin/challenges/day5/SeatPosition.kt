package challenges.day5

data class SeatPosition(val row: Int, val column: Int)

fun SeatPosition.getSeatID(): Int {
    return (this.row * 8) + this.column
}