# Bare Bones interpreter
Space Cadets challenge 2 &amp; 3: Bare Bones language interpreter

## What is this?
This is an interpreter for a language known as Bare Bones. It's a very simple language, purposefully consisting of only a few commands.

## Syntax

### Clear variable

Reset variable `X`'s value to `0`:

```
clear X;
```

<sup>Not needed to initialize a variable.</sup>

### Increment variable

Increment variable `X` by 1:

```
incr X;
```

### Decrement variable

Decrement variable `X` by 1:

```
decr X;
```

<sup>Be careful, the result can't be negative!</sup>

### Copy to a variable

Copy variable `X` to variable `Y`:

```
copy X to Y;
```

### While loops

While loop, repeating whilst the conditional expression is true:

```
incr X;
incr X;
while X not 0 do;
  decr X;
end;
```

<sup>This example will start with `X` as `2`, then loop until `X` is `0`.</sup>


## Boolean expressions

Currently, only simple boolean expressions are supported (left + right side with operator in between).
