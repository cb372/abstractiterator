# Iterator vs AbstractIterator

Investigating the impact of extending a trait directly vs extending an abstract
class that in turn extends the trait.

Inspired by [this PR](https://github.com/scala/scala/pull/6657/files).

## Classfile size

```
$ ls -l target/scala-2.11/classes
total 56
-rw-r--r--  1 chris  staff   1029 Jun 21 09:23 ExtendsAbstract.class
-rw-r--r--  1 chris  staff  22732 Jun 21 09:23 ExtendsTrait.class
```

```
$ ls -l target/scala-2.12/classes
total 64
-rw-r--r--  1 chris  staff   1029 Jun 21 09:23 ExtendsAbstract.class
-rw-r--r--  1 chris  staff  25376 Jun 21 09:23 ExtendsTrait.class
```

```
$ ls -l target/scala-2.13.0-M4/classes
total 64
-rw-r--r--  1 chris  staff   1029 Jun 21 09:35 ExtendsAbstract.class
-rw-r--r--  1 chris  staff  26172 Jun 21 09:35 ExtendsTrait.class
```

## Classfile contents

```
$ javap ExtendsAbstract
Compiled from "ExtendsAbstract.scala"
public class ExtendsAbstract<A> extends scala.collection.AbstractIterator<A> {
  public boolean hasNext();
  public A next();
  public ExtendsAbstract();
}
```

```
$ javap ExtendsTrait
Compiled from "ExtendsTrait.scala"
public class ExtendsTrait<A> implements scala.collection.Iterator<A> {
  public scala.collection.Iterator<A> seq();
  public boolean isEmpty();
  public boolean isTraversableAgain();
  public boolean hasDefiniteSize();
  public scala.collection.Iterator<A> take(int);
  public scala.collection.Iterator<A> drop(int);
  public scala.collection.Iterator<A> slice(int, int);
  public scala.collection.Iterator<A> sliceIterator(int, int);
  public <B> scala.collection.Iterator<B> map(scala.Function1<A, B>);
  public <B> scala.collection.Iterator<B> $plus$plus(scala.Function0<scala.collection.GenTraversableOnce<B>>);
  public <B> scala.collection.Iterator<B> flatMap(scala.Function1<A, scala.collection.GenTraversableOnce<B>>);
  public scala.collection.Iterator<A> filter(scala.Function1<A, java.lang.Object>);
  public <B> boolean corresponds(scala.collection.GenTraversableOnce<B>, scala.Function2<A, B, java.lang.Object>);
  public scala.collection.Iterator<A> withFilter(scala.Function1<A, java.lang.Object>);
  public scala.collection.Iterator<A> filterNot(scala.Function1<A, java.lang.Object>);
  public <B> scala.collection.Iterator<B> collect(scala.PartialFunction<A, B>);
  public <B> scala.collection.Iterator<B> scanLeft(B, scala.Function2<B, A, B>);
  public <B> scala.collection.Iterator<B> scanRight(B, scala.Function2<A, B, B>);
  public scala.collection.Iterator<A> takeWhile(scala.Function1<A, java.lang.Object>);
  public scala.Tuple2<scala.collection.Iterator<A>, scala.collection.Iterator<A>> partition(scala.Function1<A, java.lang.Object>);
  public scala.Tuple2<scala.collection.Iterator<A>, scala.collection.Iterator<A>> span(scala.Function1<A, java.lang.Object>);
  public scala.collection.Iterator<A> dropWhile(scala.Function1<A, java.lang.Object>);
  public <B> scala.collection.Iterator<scala.Tuple2<A, B>> zip(scala.collection.Iterator<B>);
  public <A1> scala.collection.Iterator<A1> padTo(int, A1);
  public scala.collection.Iterator<scala.Tuple2<A, java.lang.Object>> zipWithIndex();
  public <B, A1, B1> scala.collection.Iterator<scala.Tuple2<A1, B1>> zipAll(scala.collection.Iterator<B>, A1, B1);
  public <U> void foreach(scala.Function1<A, U>);
  public boolean forall(scala.Function1<A, java.lang.Object>);
  public boolean exists(scala.Function1<A, java.lang.Object>);
  public boolean contains(java.lang.Object);
  public scala.Option<A> find(scala.Function1<A, java.lang.Object>);
  public int indexWhere(scala.Function1<A, java.lang.Object>);
  public int indexWhere(scala.Function1<A, java.lang.Object>, int);
  public <B> int indexOf(B);
  public <B> int indexOf(B, int);
  public scala.collection.BufferedIterator<A> buffered();
  public <B> scala.collection.Iterator<A>.GroupedIterator<B> grouped(int);
  public <B> scala.collection.Iterator<A>.GroupedIterator<B> sliding(int, int);
  public int length();
  public scala.Tuple2<scala.collection.Iterator<A>, scala.collection.Iterator<A>> duplicate();
  public <B> scala.collection.Iterator<B> patch(int, scala.collection.Iterator<B>, int);
  public <B> void copyToArray(java.lang.Object, int, int);
  public boolean sameElements(scala.collection.Iterator<?>);
  public scala.collection.Traversable<A> toTraversable();
  public scala.collection.Iterator<A> toIterator();
  public scala.collection.immutable.Stream<A> toStream();
  public java.lang.String toString();
  public <B> int sliding$default$2();
  public scala.collection.immutable.List<A> reversed();
  public int size();
  public boolean nonEmpty();
  public int count(scala.Function1<A, java.lang.Object>);
  public <B> scala.Option<B> collectFirst(scala.PartialFunction<A, B>);
  public <B> B $div$colon(B, scala.Function2<B, A, B>);
  public <B> B $colon$bslash(B, scala.Function2<A, B, B>);
  public <B> B foldLeft(B, scala.Function2<B, A, B>);
  public <B> B foldRight(B, scala.Function2<A, B, B>);
  public <B> B reduceLeft(scala.Function2<B, A, B>);
  public <B> B reduceRight(scala.Function2<A, B, B>);
  public <B> scala.Option<B> reduceLeftOption(scala.Function2<B, A, B>);
  public <B> scala.Option<B> reduceRightOption(scala.Function2<A, B, B>);
  public <A1> A1 reduce(scala.Function2<A1, A1, A1>);
  public <A1> scala.Option<A1> reduceOption(scala.Function2<A1, A1, A1>);
  public <A1> A1 fold(A1, scala.Function2<A1, A1, A1>);
  public <B> B aggregate(scala.Function0<B>, scala.Function2<B, A, B>, scala.Function2<B, B, B>);
  public <B> B sum(scala.math.Numeric<B>);
  public <B> B product(scala.math.Numeric<B>);
  public <B> A min(scala.math.Ordering<B>);
  public <B> A max(scala.math.Ordering<B>);
  public <B> A maxBy(scala.Function1<A, B>, scala.math.Ordering<B>);
  public <B> A minBy(scala.Function1<A, B>, scala.math.Ordering<B>);
  public <B> void copyToBuffer(scala.collection.mutable.Buffer<B>);
  public <B> void copyToArray(java.lang.Object, int);
  public <B> void copyToArray(java.lang.Object);
  public <B> java.lang.Object toArray(scala.reflect.ClassTag<B>);
  public scala.collection.immutable.List<A> toList();
  public scala.collection.Iterable<A> toIterable();
  public scala.collection.Seq<A> toSeq();
  public scala.collection.immutable.IndexedSeq<A> toIndexedSeq();
  public <B> scala.collection.mutable.Buffer<B> toBuffer();
  public <B> scala.collection.immutable.Set<B> toSet();
  public scala.collection.immutable.Vector<A> toVector();
  public <Col> Col to(scala.collection.generic.CanBuildFrom<scala.runtime.Nothing$, A, Col>);
  public <T, U> scala.collection.immutable.Map<T, U> toMap(scala.Predef$$less$colon$less<A, scala.Tuple2<T, U>>);
  public java.lang.String mkString(java.lang.String, java.lang.String, java.lang.String);
  public java.lang.String mkString(java.lang.String);
  public java.lang.String mkString();
  public scala.collection.mutable.StringBuilder addString(scala.collection.mutable.StringBuilder, java.lang.String, java.lang.String, java.lang.String);
  public scala.collection.mutable.StringBuilder addString(scala.collection.mutable.StringBuilder, java.lang.String);
  public scala.collection.mutable.StringBuilder addString(scala.collection.mutable.StringBuilder);
  public int sizeHintIfCheap();
  public boolean hasNext();
  public A next();
  public scala.collection.GenMap toMap(scala.Predef$$less$colon$less);
  public scala.collection.GenSet toSet();
  public scala.collection.GenSeq toSeq();
  public scala.collection.GenIterable toIterable();
  public scala.collection.GenTraversable toTraversable();
  public scala.collection.TraversableOnce seq();
  public ExtendsTrait();
}
```

Examples of the bridge methods:

```
javap -c ExtendsTrait
...
  public scala.collection.GenIterable toIterable();
    Code:
       0: aload_0
       1: invokevirtual #558                // Method toIterable:()Lscala/collection/Iterable;
       4: areturn

  public scala.collection.GenTraversable toTraversable();
    Code:
       0: aload_0
       1: invokevirtual #561                // Method toTraversable:()Lscala/collection/Traversable;
       4: areturn

  public scala.collection.TraversableOnce seq();
    Code:
       0: aload_0
       1: invokevirtual #564                // Method seq:()Lscala/collection/Iterator;
       4: areturn
...
```
