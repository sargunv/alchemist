# Module alchemist

Alchemist allows you to manage physical quanities defined in the [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units). 
Like [kotlin.time.Duration](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/), alchemist models its 
quanities as a value class with a single underlying `Long` value. 

### Scalar Arithmetic 

Physical quantities expose scalar arithmetic like the following:

```kt
val power = 10.watts
println(power / 2) // "5w"
println(power * 2) // "20w"
println(-power) // "-10w"
```

and each quantity exposes typed arithmetic: 

```kt
val first = 10.wattHours
val second = 2.wattHours
println(first + second) // "12wH"
println(first - second) // "8wH"
println(second - first) // "-8wH"
println(first / second) // "5.0"
```

### Physical Arithmetic 

Physical quantities expose valid physical arithmetic defined in the International System of Units. For example: 

```kt
val energy = 10.wattHours
val power = 10.watts
println(energy / power) // "1h"

val length = 10.nanometers
println(energy / length) // "3.6TN"
```

### Custom Quantity Unit Implementation 

Every unit alchemist exposes is an interface, thus allowing you to implement non-standard or uncommonly used units: 

```kt
// Alchemist does not provide horsepower out of the box. 
object HorsePower : PowerUnit {
    override val symbol: String get() = "hp"
    override val microwattScale: Long get() = 745_699_871
}

val Int.horsepower: Power get() = this.toPower(HorsePower)

println(1.horsepower) // "745.7w"
```

Note that alchemist does not provide certain units out of the box because they're impossible to represent without losing 
precision. In the above example, 1 horsepower is actually 745,699,871.58μW.