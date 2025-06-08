## III. Document the outcome of each test case.

| Test Type | Test Case | Configuration | Result |
|-----------|-----------|---------------|--------|
| **1. Test to validate car configurations** |
| | Downforce-Focused Kit in Technical Park | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Downforce-Focussed<br>- Track: Technical Park<br>- Weather: DRY | Failed |
| | Low-Drag Kit in Speedway Oval | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Low-Drag<br>- Track: Speedway Oval<br>- Weather: DRY | Failed |
| | Wet Weather Kit in dry conditions | - Engine: Turbo V6<br>- Tyre: Soft Compound<br>- Kit: Wet Weather<br>- Track: Technical Park<br>- Weather: DRY | Failed |
| | Standard Kit in wet conditions | - Engine: Turbo V6<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Technical Park<br>- Weather: WET | Failed |
| | High downforce with low power engine | - Engine: Turbo V6<br>- Tyre: Medium Compound<br>- Kit: Downforce-Focussed<br>- Track: Technical Park<br>- Weather: DRY | Pass |
| | High downforce with high power engine | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Downforce-Focussed<br>- Track: Speedway Oval<br>- Weather: DRY | Failed |
| | Low downforce with high power engine | - Engine: V10<br>- Tyre: Medium Compound<br>- Kit: Low-Drag<br>- Track: Technical Park<br>- Weather: DRY | Failed |
| | Low downforce with low power engine | - Engine: V10<br>- Tyre: Medium Compound<br>- Kit: Low-Drag<br>- Track: Technical Park<br>- Weather: DRY | Failed |
| | Hard Compound in wet conditions | - Engine: Turbo V6<br>- Tyre: Hard Compound<br>- Kit: Wet Weather<br>- Track: Technical Park<br>- Weather: WET | Failed |
| | Soft Compound in high-speed track | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Low-Drag<br>- Track: Speedway Oval<br>- Weather: DRY | Failed |
| **2. Test strategy outcomes under various race scenarios** |
| | Dry Conditions | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Coastal Run<br>- Weather: DRY | Pass |
| | Wet Conditions | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Coastal Run<br>- Weather: WET | Pass |
| | Short Race | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Coastal Run<br>- Weather: DRY | Failed |
| | Long Race | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Coastal Run<br>- Weather: DRY | Pass |
| | Twisty Track | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Technical Park<br>- Weather: DRY | Pass |
| | Straight Track | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Speedway Oval<br>- Weather: DRY | Pass |
| **3. Test for edge cases** |
| | Negative fuel | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Coastal Run<br>- Fuel: -1 | Failed |
| | Negative laps | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Laps: -1 | Failed |
| | Non-existent engine | - Engine: non-existent<br>- Tyre: Soft Compound<br>- Kit: Standard | Failed |
| | Non-existent tyre | - Engine: V10<br>- Tyre: non-existent<br>- Kit: Standard | Failed |
| | Non-existent aero kit | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: non-existent | Failed |
| | Non-existent track | - Engine: V10<br>- Tyre: Soft Compound<br>- Track: non-existent | Failed |
| | Non-existent weather | - Engine: V10<br>- Tyre: Soft Compound<br>- Weather: null | Failed |
| **4. Test multiple races to test the accuracy and consistency** |
| | Race 1 (Technical Park) | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Technical Park | Failed |
| | Race 2 (Speedway Oval) | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Speedway Oval | Failed |
| | Race 3 (Mountain Pass) | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Mountain Pass | Failed |
| | Race 4 (City Circuit) | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: City Circuit | Failed |
| | Race 5 (Coastal Run) | - Engine: V10<br>- Tyre: Soft Compound<br>- Kit: Standard<br>- Track: Coastal Run | Failed |


## IV. Indicate the coverage of the test cases written.

![alt text](image.png)

As the snapshot show, Method Coverage is 85% and Line Coverage is 97%.