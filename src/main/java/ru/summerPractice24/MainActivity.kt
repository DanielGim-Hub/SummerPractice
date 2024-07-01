package ru.summerPractice24
import kotlin.random.Random

// Основной класс Автомобиль
open class Car(val brand: String, val model: String, val year: Int, val drive: String, val power: Int, val type: String) {
    open fun getInfo() {
        println("Марка: $brand, Модель: $model, Год выпуска: $year, Привод: $drive, Мощность: $power л.с, Тип: $type")
    }
}

// Производные классы
class Sedan(brand: String, model: String, year: Int, drive: String, power: Int, type: String, val maxSpeed: Int) : Car(brand, model, year, drive, power, type) {
    override fun getInfo() {
        println("Марка: $brand, Модель: $model, Год выпуска: $year, Привод: $drive, Мощность: $power л.с, Тип: $type, Максимальная скорость: $maxSpeed")
    }
}

class SUV(brand: String, model: String, year: Int, drive: String, power: Int, type: String, val comfortLevel: Int) : Car(brand, model, year, drive, power, type) {
    override fun getInfo() {
        println("Марка: $brand, Модель: $model, Год выпуска: $year, Привод: $drive, Мощность: $power л.с, Тип: $type, Уровень комфорта: $comfortLevel")
    }
}

class Coupe(brand: String, model: String, year: Int, drive: String, power: Int, type: String, val acceleration: Int) : Car(brand, model, year, drive, power, type) {
    override fun getInfo() {
        println("Марка: $brand, Модель: $model, Год выпуска: $year, Привод: $drive, Мощность: $power л.с, Тип: $type, Разгон: $acceleration")
    }
}

class Truck(brand: String, model: String, year: Int, drive: String, power: Int, type: String, val loadCapacity: Int) : Car(brand, model, year, drive, power, type) {
    override fun getInfo() {
        println("Марка: $brand, Модель: $model, Год выпуска: $year, Привод: $drive, Мощность: $power л.с, Тип: $type,Грузоподъемность: $loadCapacity")
    }
}

fun createRandomCar(): Car {
    val brands = listOf("Toyota", "Ford", "BMW", "Tesla")
    val models = listOf("Model S", "Mustang", "X5", "Corolla")
    val drives = listOf("Передний", "Задний", "Полный")
    val types = listOf("Sedan", "SUV", "Coupe", "Truck")
    val random = Random

    val brand = brands.random()
    val model = models.random()
    val year = random.nextInt(1995, 2024)
    val drive = drives.random()
    val power = random.nextInt(50, 300)
    val type = types.random()

    return when (type) {
        "Sedan" -> Sedan(brand, model, year, drive, power, type, random.nextInt(180, 300))
        "SUV" -> SUV(brand, model, year, drive, power, type, random.nextInt(1, 10))
        "Coupe" -> Coupe(brand, model, year, drive, power, type, random.nextInt(3, 7))
        "Truck" -> Truck(brand, model, year, drive, power, type, random.nextInt(1000, 10000))
        else -> Car(brand, model, year, drive, power, type)
    }
}

fun raceRound(cars: List<Car>): List<Car> {
    val winners = mutableListOf<Car>()
    val random = Random
    val shuffledCars = cars.shuffled()

    for (i in shuffledCars.indices step 2) {
        if (i + 1 < shuffledCars.size) {
            val car1 = shuffledCars[i]
            val car2 = shuffledCars[i + 1]
            val winner = if (random.nextBoolean()) car1 else car2
            println("Гонка между ${car1.brand} ${car1.model} и ${car2.brand} ${car2.model}, Победитель: ${winner.brand} ${winner.model}")
            winners.add(winner)
        } else {
            val car = shuffledCars[i]
            winners.add(car)
            println("${car.brand} ${car.model} - Нет пары, следующий круг")
        }
    }

    return winners
}

fun getUserInput(prompt: String): String {
    print(prompt)
    return readLine() ?: ""
}

fun main() {
    val input = getUserInput("Введите количество автомобилей для гонки: ")
    val numCars = input.toIntOrNull()

    if (numCars != null && numCars > 0) {
        val cars = List(numCars){
            createRandomCar()
        }
        var round = 1
        var remainingCars = cars

        while (remainingCars.size > 1) {
            println("--- Раунд $round ---")
            remainingCars = raceRound(remainingCars)
            round++
        }

        println("Победитель: ${remainingCars.first().brand} ${remainingCars.first().model}")
    } else {
        println("Введите корректное количество автомобилей.")
    }
}









