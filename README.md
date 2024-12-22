# Alchemist

Manage physical units. Inspired by kotlin.time.Duration. Alchemist allow type safe arithmetic between different 
physical quantities defined in the [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units#).

```kt
val time: Duration = 10.seconds
val length: Length = 10.kilometers
val velocity: Velocity = length / time
val acceleration: Acceleration = velocity / time
val mass: Mass = 10.kilograms
val force: Force = acceleration * mass
val energy: Energy = force * length 
val power: Power = energy / time
val area: Area = length * length 
val volume: Volume = length * length * length 
```