package me.xdark.vrtvm;

import me.xdark.vrtvm.interpreter.*;
import org.objectweb.asm.tree.AbstractInsnNode;

import static org.objectweb.asm.Opcodes.*;

public final class Instructions {
    private final InstructionInterpreter<?>[] interpreters;

    Instructions() {
        InstructionInterpreter<?>[] interpreters = this.interpreters = new InstructionInterpreter[200];
        interpreters[NOP] = new NopHandler();
        interpreters[ACONST_NULL] = new NullConstHandler();
        interpreters[ICONST_M1] = new IntConstantHandler();
        interpreters[ICONST_0] = new IntConstantHandler();
        interpreters[ICONST_1] = new IntConstantHandler();
        interpreters[ICONST_2] = new IntConstantHandler();
        interpreters[ICONST_3] = new IntConstantHandler();
        interpreters[ICONST_4] = new IntConstantHandler();
        interpreters[ICONST_5] = new IntConstantHandler();
        interpreters[LCONST_0] = new LongConstantHandler();
        interpreters[LCONST_1] = new LongConstantHandler();
        interpreters[FCONST_0] = new FloatConstantHandler();
        interpreters[FCONST_1] = new FloatConstantHandler();
        interpreters[FCONST_2] = new FloatConstantHandler();
        interpreters[DCONST_0] = new DoubleConstantHandler();
        interpreters[DCONST_1] = new DoubleConstantHandler();
        interpreters[BIPUSH] = new BytePushHandler();
        interpreters[SIPUSH] = new ShortPushHandler();
        interpreters[LDC] = new LdcHandler();
        interpreters[ILOAD] = new IntLoadHandler();
        interpreters[LLOAD] = new LongLoadHandler();
        interpreters[FLOAD] = new FloatLoadHandler();
        interpreters[DLOAD] = new DoubleLoadHandler();
        interpreters[ALOAD] = new ObjectLoadHandler();
        interpreters[POP] = new PopHandler();
        interpreters[POP2] = new Pop2Handler();
        interpreters[DUP] = new DupHandler();
        interpreters[DUP_X1] = new DupX1Handler();
        interpreters[DUP_X2] = new DupX2Handler();
        interpreters[DUP2] = new Dup2Handler();
        interpreters[DUP2_X1] = new Dup2X1Handler();
        interpreters[DUP2_X2] = new Dup2X2Handler();
        interpreters[SWAP] = new SwapHandler();
        interpreters[IADD] = new IntMathHandler(Integer::sum);
        interpreters[LADD] = new LongMathHandler(Long::sum);
        interpreters[FADD] = new FloatMathHandler(Float::sum);
        interpreters[DADD] = new DoubleMathHandler(Double::sum);
        interpreters[ISUB] = new IntMathHandler((v1, v2) -> v1 - v2);
        interpreters[LSUB] = new LongMathHandler((v1, v2) -> v1 - v2);
        interpreters[FSUB] = new FloatMathHandler((v1, v2) -> v1 - v2);
        interpreters[DSUB] = new DoubleMathHandler((v1, v2) -> v1 - v2);
        interpreters[IMUL] = new IntMathHandler((v1, v2) -> v1 * v2);
        interpreters[LMUL] = new LongMathHandler((v1, v2) -> v1 * v2);
        interpreters[FMUL] = new FloatMathHandler((v1, v2) -> v1 * v2);
        interpreters[DMUL] = new DoubleMathHandler((v1, v2) -> v1 * v2);
        interpreters[IDIV] = new IntMathHandler((v1, v2) -> v1 / v2);
        interpreters[LDIV] = new LongMathHandler((v1, v2) -> v1 / v2);
        interpreters[FDIV] = new FloatMathHandler((v1, v2) -> v1 / v2);
        interpreters[DDIV] = new DoubleMathHandler((v1, v2) -> v1 / v2);
        interpreters[IREM] = new IntMathHandler((v1, v2) -> v1 % v2);
        interpreters[LREM] = new LongMathHandler((v1, v2) -> v1 % v2);
        interpreters[FREM] = new FloatMathHandler((v1, v2) -> v1 % v2);
        interpreters[DREM] = new DoubleMathHandler((v1, v2) -> v1 % v2);
        interpreters[INEG] = new IntNegateHandler();
        interpreters[LNEG] = new LongNegateHandler();
        interpreters[FNEG] = new FloatNegateHandler();
        interpreters[DNEG] = new DoubleNegateHandler();
        interpreters[ISHL] = new IntMathHandler((v1, v2) -> v1 << v2);
        interpreters[LSHL] = new LongMathHandler((v1, v2) -> v1 << v2);
        interpreters[ISHR] = new IntMathHandler((v1, v2) -> v1 >> v2);
        interpreters[LSHR] = new LongMathHandler((v1, v2) -> v1 >> v2);
        interpreters[IUSHR] = new IntMathHandler((v1, v2) -> v1 >>> v2);
        interpreters[LUSHR] = new LongMathHandler((v1, v2) -> v1 >>> v2);
        interpreters[IAND] = new IntMathHandler((v1, v2) -> v1 & v2);
        interpreters[LAND] = new LongMathHandler((v1, v2) -> v1 & v2);
        interpreters[IOR] = new IntMathHandler((v1, v2) -> v1 | v2);
        interpreters[LOR] = new LongMathHandler((v1, v2) -> v1 | v2);
        interpreters[IXOR] = new IntMathHandler((v1, v2) -> v1 ^ v2);
        interpreters[LXOR] = new LongMathHandler((v1, v2) -> v1 ^ v2);
        interpreters[IINC] = new IntIncrementHandler();
        interpreters[I2L] = new IntToLongHandler();
        interpreters[I2F] = new IntToFloatHandler();
        interpreters[I2D] = new IntToDoubleHandler();
        interpreters[L2I] = new LongToIntHandler();
        interpreters[L2F] = new LongToFloatHandler();
        interpreters[L2D] = new LongToDoubleHandler();
        interpreters[F2I] = new FlaotToIntHandler();
        interpreters[F2L] = new FlaotToLongHandler();
        interpreters[F2D] = new FloatToDoubleHandler();
        interpreters[D2I] = new DoubleToIntHandler();
        interpreters[D2L] = new DoubleToLongHandler();
        interpreters[D2F] = new DoubleToFloatHandler();
        interpreters[I2B] = new IntToByteHandler();
        interpreters[I2C] = new IntToCharHandler();
        interpreters[I2S] = new IntToShortHandler();
        interpreters[LCMP] = new LongCompareHandler();
        interpreters[FCMPL] = new FloatCompareLowHandler();
        interpreters[FCMPG] = new FloatCompareGreatHandler();
        interpreters[DCMPL] = new DoubleCompareLowHandler();
        interpreters[DCMPG] = new DoubleCompareGreatHandler();
        interpreters[IFEQ] = new IntJumpHandler(v -> v == 0);
        interpreters[IFNE] = new IntJumpHandler(v -> v != 0);
        interpreters[IFLT] = new IntJumpHandler(v -> v < 0);
        interpreters[IFGE] = new IntJumpHandler(v -> v >= 0);
        interpreters[IFGT] = new IntJumpHandler(v -> v > 0);
        interpreters[IFLE] = new IntJumpHandler(v -> v <= 0);
        interpreters[IF_ICMPEQ] = new BiIntJumpHandler((v1, v2) -> v1 == v2);
        interpreters[IF_ICMPNE] = new BiIntJumpHandler((v1, v2) -> v1 != v2);
        interpreters[IF_ICMPLT] = new BiIntJumpHandler((v1, v2) -> v1 < v2);
        interpreters[IF_ICMPGE] = new BiIntJumpHandler((v1, v2) -> v1 >= v2);
        interpreters[IF_ICMPGT] = new BiIntJumpHandler((v1, v2) -> v1 > v2);
        interpreters[IF_ICMPLE] = new BiIntJumpHandler((v1, v2) -> v1 <= v2);
        interpreters[IF_ACMPEQ] = new BiObjectJumpHandler((v1, v2) -> v1 == v2);
        interpreters[IF_ACMPNE] = new BiObjectJumpHandler((v1, v2) -> v1 != v2);
        interpreters[GOTO] = new JumpHandler();
        // TODO JSR
        // TODO RET (who uses that?!)
        interpreters[TABLESWITCH] = new TableSwitchHandler();
        interpreters[LOOKUPSWITCH] = new LookupSwitchHandler();
        interpreters[IRETURN] = new IntReturnHandler();
        interpreters[LRETURN] = new LongReturnHandler();
        interpreters[FRETURN] = new FloatReturnHandler();
        interpreters[DRETURN] = new DoubleReturnHandler();
        interpreters[ARETURN] = new ObjectReturnHandler();
        interpreters[RETURN] = new VoidReturnHandler();
    }

    public <T extends AbstractInsnNode> InstructionInterpreter<T> forOpcode(int opcode) {
        return (InstructionInterpreter<T>) interpreters[opcode];
    }
}
