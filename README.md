# Bare Bones interpreter
Space Cadets challenge 2 &amp; 3: Bare Bones language interpreter

## What is this?
This is an interpreter for a language known as Bare Bones. It's a very simple language, purposefully consisting of only a few commands.

## Syntax

Reset variable `X`'s value to `0`. Not needed to initialize a variable.

```
clear X;
```

Increment variable `X` by 1.

```
incr X;
```

Decrement variable `X` by 1. Be careful, the result can't be negative!

```
decr X;
```

Copy variable `X` to variable `Y`.

```
copy X to Y;
```

While loop, repeating whilst the conditional expression is true. This will start with `X` as `2`, then loop until `X` is `0`.

```
incr X;
incr X;
while X not 0 do;
  decr X;
end;
```

## Boolean expressions

Currently only simple boolean expressions are supported (left + right side with operator in between).
