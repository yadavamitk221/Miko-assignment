package micoAssignment;
import java.util.HashMap;
import java.util.Map;

public class AssemblyInterpreter {
    private static final Map<String, Integer> registers = new HashMap<>();

    public static void main(String[] args) {
        String program = "MV REG1,#2000\n" +
                "MV REG2,#4000\n" +
                "ADD REG1,REG2\n" +
                "ADD REG1,600\n" +
                "SHOW REG1";

        executeProgram(program);
    }

    private static void executeProgram(String program) {
        String[] instructions = program.split("\n");

        for (String instruction : instructions) {
            instruction = instruction.trim();

            if (instruction.startsWith("MV")) {
                executeMVInstruction(instruction);
            } else if (instruction.startsWith("ADD")) {
                executeADDInstruction(instruction);
            } else if (instruction.startsWith("SHOW")) {
                executeSHOWInstruction(instruction);
            }
        }
    }

    private static void executeMVInstruction(String instruction) {
        String[] parts = instruction.split("\\s*,\\s*");
        String register = parts[0].substring(3).trim();
        int value = Integer.parseInt(parts[1].substring(1).trim());

        registers.put(register, value);
    }

    private static void executeADDInstruction(String instruction) {
        String[] parts = instruction.split("\\s*,\\s*");
        String destination = parts[0].substring(3).trim();
        String source = parts[1].trim();

        int result = getRegisterValue(destination);

        if (source.startsWith("#")) {
            int constant = Integer.parseInt(source.substring(1).trim());
            result += constant;
        } else {
            result += getRegisterValue(source);
        }

        registers.put(destination, result);
    }

    private static void executeSHOWInstruction(String instruction) {
        String[] parts = instruction.split("\\s+");
        String register = parts[1].trim();

        int value = getRegisterValue(register);
        System.out.println(register + ": " + value);
    }

    private static int getRegisterValue(String register) {
        return registers.getOrDefault(register, 0);
    }
}

