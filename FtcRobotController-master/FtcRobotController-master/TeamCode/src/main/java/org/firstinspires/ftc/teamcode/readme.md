How to use ModularScripts:

GlobalScript:

has the main variables that everything uses

ex: DriveTrain Motor Variables

----------------------------------------------

MotorScript:

Has methods for single motors
    
    setMotorDir:
    
    The "Motor" var is the input var for the motor you want to use
    
    The "dir" var is the input var for what direction you want to set the motor too
    if dir equals 1 it will set it to Forward
    if dir equals 2 it will set it to Reverse
    
    ControlMotorPosBased:
    
    The "Motor" var is the input var for the motor you want to use
    The "vel" var is the input var for the amount of velocity you want the motor to hav
    the "button" var is the input var for the button you want to be pressed in order to activate the motor
    
    
    ControlMotorPwrBased:
    
    Same as ControlMotorPosBased() but uses the setPower() method instead
    The "Motor" var is the input var for the motor you want to use
    The "pwr" var is the input var for the amount of Power you want the motor to hav
    the "button" var is the input var for the button you want to be pressed in order to activate the motor

---

DriveTrain:

Has methods for the driveTrain
    posBasedMovementInit:
    //initializes movement that uses velocity
    //only use if using velocity based movement
    
    drivePosBase:
    
    drives using the setVelocity Function
    vel must be a positive number
    if F_B_L_R is 1 it will go forward
    if F_B_L_R is 2 it will go backward
    if F_B_L_R is 3 it will go left
    if F_B_L_R is 4 it will go right
    
    drivePwrBase:
    
    drives using the setPower Function
    pwr must be a positive number
    if F_B_L_R is 1 it will go forward
    if F_B_L_R is 2 it will go backward
    if F_B_L_R is 3 it will go left
    if F_B_L_R is 4 it will go right

-------




