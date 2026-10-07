new AtomicTestBuilder<>(NonAtomicCounter::new)
.addUpdate(NonAtomicCounter::incrementAndGet)
.addWrite(NonAtomicCounter::increment)
.addReadOnly(NonAtomicCounter::getCount)
.runTests();


new AtomicTestBuilder<>(AtomicCounter::new)
.addUpdate(AtomicCounter::incrementAndGet)
.addWrite(AtomicCounter::increment)
.addReadOnly(AtomicCounter::getCount)
.withParallelMethodCalls(3)
.runTests();

ADT
Constructor
Arguments

method call
commentar 

eins zu eins builder