import RPi.GPIO as GPIO
import time

class CNC:
    def __init__(self, motor_pins):
        self.motor_pins = motor_pins
        GPIO.setmode(GPIO.BCM)
        for pin in motor_pins:
            GPIO.setup(pin, GPIO.OUT)

    def set_motor_direction(self, pin, direction):
        if direction == "CW":
            GPIO.output(pin, GPIO.HIGH)
        else:
            GPIO.output(pin, GPIO.LOW)

    def move_motor(self, pin, direction, duration):
        self.set_motor_direction(pin, direction)
        print(f"Moving motor {pin} in {direction} direction for {duration} seconds")
        time.sleep(duration)

    def cleanup(self):
        GPIO.cleanup()

# Main program
if __name__ == "__main__":
    # Define motor pin constants
    MOTOR_A_PIN = 17
    MOTOR_B_PIN = 27

    # Initialize CNC instance with motor pins
    cnc = CNC([MOTOR_A_PIN, MOTOR_B_PIN])

    try:
        # Move motor A forward for 2 seconds
        cnc.move_motor(MOTOR_A_PIN, "CW", 2)

        # Move motor B backward for 1 second
        cnc.move_motor(MOTOR_B_PIN, "CCW", 1)

    finally:
        # Clean up GPIO pins
        cnc.cleanup()

    print("Program finished")
